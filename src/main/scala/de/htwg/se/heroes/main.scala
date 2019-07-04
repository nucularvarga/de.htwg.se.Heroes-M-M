package de.htwg.se.heroes

import com.google.inject.Guice
import de.htwg.se.heroes.aview.Tui
import de.htwg.se.heroes.controllerComponent.{ControllerInterface, FieldChanged, GameStart}
import javafx.embed.swing.JFXPanel

import scala.io.StdIn.readLine

object main {
  val injector = Guice.createInjector(new HeroesModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  //val controller = new Controller(new Field(9), new Arena(8, 20))
  val tui = new Tui(controller)
  controller.publish(new GameStart)


  def main(args: Array[String]): Unit = {
    var input: String = ""
    do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
    System.exit(0)
  }

}