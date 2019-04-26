package de.htwg.se.heroes.aview

import de.htwg.se.heroes.controller._
import de.htwg.se.heroes.util.Observer

class Tui(controller: Controller) extends Observer {

  controller.add(this)

  def processInputLine(input: String):Unit = {
    input match {
      case "q" =>
      case "n"=> controller.createNewPlayground(10)
      case "w" => controller.move(Direction.Up)
      case "a" => controller.move(Direction.Left)
      case "s" => controller.move(Direction.Down)
      case "d" => controller.move(Direction.Right)
      case "t" => controller.showStats()
      case _   =>
    }
  }

  override def update: Unit =  println(controller.playgroundToString)
}