package hello.routes

import org.scalatra.{ ScalatraServlet, FutureSupport }
import scala.concurrent.ExecutionContext
import java.util.concurrent.Executors
import slick.jdbc.JdbcBackend
import scalaz.{ Success, Failure }

class Index(implicit val executor0: ExecutionContext)
    extends ScalatraServlet with FutureSupport {
  import hello.{ model, html }
  import slick.jdbc.JdbcBackend.Database

  override implicit val executor = ExecutionContext.fromExecutorService(
      Executors.newFixedThreadPool(1))
  val db = Database.forURL("jdbc:h2:~/hello.db", Map[String, String]())

  get("/initdb") {
    for (_ <- db.run(model.Book.schema))
      yield "OK"
  }

  get("/books") {
    for (books <- db.run(model.Book.listAll))
      yield html.BookList(books.toList)
  }

  post("/books") {
    model.Book.validate(params)(model.Book(_, _)) match {
      case Success(book) =>
        for (_ <- db.run(model.Book.create(book)))
          yield redirect("/books")
      case Failure(messages) =>
        halt(400, body = html.ValidationError(messages))
    }
  }
}
