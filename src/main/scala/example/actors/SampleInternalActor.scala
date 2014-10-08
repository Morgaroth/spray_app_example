package example.actors

import akka.actor.Actor

import example.actors.SampleInternalActor._

object SampleInternalActor {
  case object Compute
  case class Computed(value: Double)
}

class SampleInternalActor extends Actor {

  override def receive: Receive = {
    case Compute =>
      // sth stuff
      sender() ! Computed(6.66)
  }
}
