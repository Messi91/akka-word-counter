package normalsolution

import java.io.File

import scala.io.Source._

/**
  * Created by mesfinmebrate on 11/09/2016.
  */
object NormalWordCounter extends App {

  def wordsInFile(filename: String): Int = {
    val file = new File(getClass.getClassLoader.getResource(filename).getFile)
    fromFile(file).getLines.map { line =>
      wordsInLine(line)
    }.sum
  }

  def wordsInLine(line: String): Int = {
    line.split(" ").length
  }

  def printAnswer: Unit = {
    val result = wordsInFile("odyssey.txt")
    println(s"Total number of words = $result")
  }

  printAnswer
}