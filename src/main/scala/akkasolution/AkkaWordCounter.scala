package akkasolution

import akka.actor.{ActorSystem, Props}

/**
  * Created by mesfinmebrate on 11/09/2016.
  */
object AkkaWordCounter extends App {

  import akka.util.Timeout
  import scala.concurrent.duration._
  import akka.pattern.ask
  import akka.dispatch.ExecutionContexts._

  override def main(args: Array[String]) {
    val system = ActorSystem("System")
    val actor = system.actorOf(Props(new WordCounterActor("odyssey.txt")))
    implicit val timeout = Timeout(25 seconds)
    implicit val ec = global
    val future = actor ? StartProcessFileMessage()
    future.map { result =>
      println("Total number of words " + result)
      system.terminate()
    }
  }
}