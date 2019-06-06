package de.htwg.se.heroes

import de.htwg.se.heroes.aview.{GUI, Gui_Wrapper, Tui}
import de.htwg.se.heroes.model._
import de.htwg.se.heroes.controller.Controller
import javafx.embed.swing.JFXPanel
import scala.io.StdIn.readLine

object main {
  val controller = new Controller(new Field(9), new Arena(8, 20))
  val tui = new Tui(controller)
  val gui = new Gui_Wrapper(controller)

  new JFXPanel()

  //controller.notifyObservers
  def main(args: Array[String]): Unit = {
    gui.show()
    var input: String = ""
    do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}