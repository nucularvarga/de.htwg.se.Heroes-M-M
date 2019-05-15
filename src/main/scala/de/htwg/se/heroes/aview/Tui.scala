package de.htwg.se.heroes.aview

import de.htwg.se.heroes.controller._
import de.htwg.se.heroes.model._
import de.htwg.se.heroes.util.Observer

class Tui(controller: Controller) extends Observer {

  controller.add(this)

  def processInputLine(input: String):Unit = {

    val inputsplit = input.split(",")

   // val pattern = "([a-z]) ([0-9]+)".r
   // val pattern(cmd, number) = input

    inputsplit(0) match {
      case "q" =>
      // TODO case "add" => addspieler(name)
      case "n" => controller.createNewField(9)
      case "i" => controller.init()
      case "w" => controller.action(Event.MoveUp)
      case "a" => controller.action(Event.MoveLeft)
      case "s" => controller.action(Event.MoveDown)
      case "d" => controller.action(Event.MoveRight)
      case "t" => controller.showStats()
      case "b" => controller.openShop(inputsplit(1).toInt)
      case _   =>
    }
  }

  override def update: Unit =  println(controller.playgroundToString)
}