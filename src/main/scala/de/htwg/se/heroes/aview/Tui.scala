package de.htwg.se.heroes.aview

import de.htwg.se.heroes.controller._
import de.htwg.se.heroes.util.Observer

class Tui(controller: Controller) extends Observer {

  controller.add(this)

  def processInputLine(input: String):Unit = {
    input match {
      case "q" =>
      // TODO case "add" => addspieler(name)
      case "n"=> controller.createNewPlayground(10)
      case "w" => controller.action(Direction.Up)
      case "a" => controller.action(Direction.Left)
      case "s" => controller.action(Direction.Down)
      case "d" => controller.action(Direction.Right)
      case "t" => controller.showStats()
      case _   =>
    }
  }

  override def update: Unit =  println(controller.playgroundToString)
}