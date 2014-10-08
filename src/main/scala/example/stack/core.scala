package example.stack

import akka.actor.ActorSystem
import com.typesafe.config.{Config, ConfigFactory}

trait Core {
  implicit def system: ActorSystem
}

trait BootedCore extends Core {
  val APPLICATION_NAME: Option[String] = Some("alamakota")
  val name = APPLICATION_NAME.getOrElse(throw new RuntimeException("Provide APPLICATION_NAME"))
  implicit lazy val system = ActorSystem(name)
  sys.addShutdownHook(system.shutdown())
}

