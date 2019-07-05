package de.htwg.se.heroes

import com.google.inject.Guice
import de.htwg.se.heroes.aview.{JFXGui, Tui}
import de.htwg.se.heroes.controllerComponent.{ControllerInterface, FieldChanged, GameStart}
import javafx.embed.swing.JFXPanel

import scala.io.StdIn.readLine

object main {
  val injector = Guice.createInjector(new HeroesModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val tui = new Tui(controller)
  val gui = new JFXGui(controller)
  controller.publish(new GameStart)


  def main(args: Array[String]): Unit = {
    startJFGUI(gui)
    var input: String = ""
    do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
    System.exit(0)
  }

  def startJFGUI(gui: JFXGui) = {
    new Thread(new Runnable {
      def run(): Unit = {
        gui.main(Array())
      }
    }).start()
  }
}