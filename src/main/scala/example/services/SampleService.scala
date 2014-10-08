package example.services

import akka.actor.ActorRef
import akka.util.Timeout
import spray.httpx.SprayJsonSupport
import spray.routing.{Directives, Route}

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

/**
 * Serwisy są klasami, które adaptują requesty HTTP i pośredniczą w wymianie danych z aktorami, które są nieświadome tego, że działają w aplikacji WEB
 */
class SampleService(actor: ActorRef)(implicit exContext: ExecutionContext) extends Directives with SprayJsonSupport{

  override val pathEnd = pathEndOrSingleSlash
  implicit val timeout: Timeout = 20.seconds


  // metoda typu Route buduje drzewo urli, które obsługuje
  def route: Route =
    pathPrefix("") {
      pathEnd(get(complete("Hello from analytics service")))
    }
}
