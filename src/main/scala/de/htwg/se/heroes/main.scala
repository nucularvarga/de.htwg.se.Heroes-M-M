package de.htwg.se.heroes

import de.htwg.se.heroes.aview.{GUI, Tui}
import de.htwg.se.heroes.model._
import de.htwg.se.heroes.controller.Controller


import scala.io.StdIn.readLine

object main{
  val controller = new Controller(new Field(9), new Arena(8, 20))
  val tui = new Tui(controller)
  //val gui = new GUI(controller)

  //controller.notifyObservers
  def main(args: Array[String]): Unit = {
    var input: String = ""
    do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}