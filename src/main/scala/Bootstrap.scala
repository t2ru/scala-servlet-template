import javax.servlet.ServletContext
import org.scalatra.LifeCycle
import akka.actor.ActorSystem

class ScalatraBootstrap extends LifeCycle {

  val system = ActorSystem("main")
  implicit val executor = system.dispatcher

  override def init(context: ServletContext) {
    context.mount(new hello.routes.Index, "/")
  }
}
