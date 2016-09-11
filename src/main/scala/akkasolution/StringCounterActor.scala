package akkasolution

import akka.actor.Actor
import akka.actor.Actor.Receive

/**
  * Created by mesfinmebrate on 11/09/2016.
  */
case class ProcessStringMessage(string: String)
case class StringsProcessedMessage(answer: Int)

class StringCounterActor extends Actor {

  def receive = {
    case ProcessStringMessage(line) => {
      val answer = line.split(" ").length
      sender ! (StringsProcessedMessage(answer))
    }
    case _ => println("Error: Message not recognized.")
  }
}