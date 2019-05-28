package de.htwg.se.heroes

import de.htwg.se.heroes.aview.{GUI, Tui}
import de.htwg.se.heroes.model._
import de.htwg.se.heroes.controller.Controller

import scala.io.StdIn.readLine

object main{
  val controller = new Controller(new Field(9), new Arena(8, 20))
  val tui = new Tui(controller)

  //controller.notifyObservers
  def main(args: Array[String]): Unit = {
    val gui = new GUI
    var input: String = ""
    gui.start
    do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}