package hello.model

case class Book(
  id: Int,
  title: String)

object Book extends ValidationUtil {

  import slick.driver.H2Driver.api._
  import slick.jdbc.GetResult

  def schema =
    sqlu"CREATE TABLE book (id INTEGER PRIMARY KEY, title TEXT)"

  implicit val genBook =
    GetResult { r => Book(r.<<, r.<<) }

  def listAll =
    sql"SELECT id, title FROM book".as[Book]

  def create(book: Book) =
    sqlu"INSERT INTO book VALUES(${book.id}, ${book.title})"

  import scalaz.Scalaz.ToApplyOpsUnapply

  def validate(params: Map[String, String]) =
    validateInt(params, "id", "ID") |@|
    validateNonEmptyString(params, "title", "タイトル")
}