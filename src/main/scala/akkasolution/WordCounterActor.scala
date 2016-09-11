package akkasolution

import java.io.File

import akka.actor.{Actor, ActorRef, Props}

import scala.io.Source._

/**
  * Created by mesfinmebrate on 11/09/2016.
  */
case class StartProcessFileMessage()

class WordCounterActor(filename: String) extends Actor {

  private var running = false
  private var totalLines = 0
  private var linesProcessed = 0
  private var result = 0
  private var fileSender: Option[ActorRef] = None

  def receive = {
    case StartProcessFileMessage() => {
      if (running) {
        println("Warning: duplicate start message received")
      }
      else {
        running = true
        fileSender = Some(sender)
        val file = new File(getClass.getClassLoader.getResource(filename).getFile)
        fromFile(file).getLines.foreach { line =>
          (context.actorOf(Props[StringCounterActor])) ! ProcessStringMessage(line)
          totalLines += 1
        }
      }
    }
    case StringsProcessedMessage(answer) => {
      result += answer
      linesProcessed += 1
      if (totalLines == linesProcessed) {
        fileSender.map(_ ! result)
      }
    }
    case _ => println("Error: Message not recognized.")
  }
}