package example.actors

import akka.actor.{Props, Actor}

import example.actors.SampleActor._

/**
 * object i class służą rozdzieleniu elementów należących do obiektu od należących do typu (zwykłe <-> static)
 * dlatego istnieje object i class o tej samej nazwie
 */

/* podejrzałem gdzieś pattern, że do class Actor wsadza się logikę aktora do metod receive
 * a wiadomości, które aktor odbiera, wsadza się do object actor (w literaturze taki object jest nazywany
 * companion object for class)
 */
object SampleActor {
  case object Message
  case object GetValue
}

class SampleActor extends Actor {

  // definicja zmiennej (var) z wartością początkową, żeby kompilator rozpoznał typ zmiennej
  var value = 0.0
  //definicja zmiennej z podanym typem, _ w takim kontekście oznacza wartość nullową adekwatną do typu
  var value2: Double = _

  // context jest bardzo powerfull zmienną odziedziczoną z Actor, pozwala m.in. na instancjonowanie aktorów
  val helperActor = context.actorOf(Props[SampleInternalActor])

  // metoda typu Receive sluży do odbierania wiadomości wysyłanych do aktora
  override def receive: Receive = {
    case Message =>
      // ! do operator wysyłania wiadomości, składnia: AKTOR ! WIADOMOSC
      helperActor ! SampleInternalActor.Compute

    case SampleInternalActor.Computed(computedValue) =>
      println(s"internal actor computed $computedValue")
      value = computedValue

    case GetValue =>
      sender() ! value
  }
}
