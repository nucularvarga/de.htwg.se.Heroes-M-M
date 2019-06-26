package de.htwg.se.heroes.aview

import de.htwg.se.heroes.controllerComponent._

import scala.swing.Reactor

class Tui(controller: ControllerInterface) extends Reactor {

  listenTo(controller)



  def processInputLine(input: String):Unit = {

    val inputsplit = input.split(",")

   // val pattern = "([a-z]) ([0-9]+)".r
   // val pattern(cmd, number) = input

    inputsplit(0) match {
      case "q" =>
      // TODO case "add" => addspieler(name)
      case "n" => controller.createNewField(9)
      case "i" => controller.init()
      case "r" => controller.undo
      case "z" => controller.redo
      case "w" => controller.action(UIEvent.MoveUp)
      case "a" => controller.action(UIEvent.MoveLeft)
      case "s" => controller.action(UIEvent.MoveDown)
      case "d" => controller.action(UIEvent.MoveRight)
      case "t" => controller.showStats()
      case "b" => controller.openShop(inputsplit(1).toInt)
      case _   =>
    }
  }

  reactions += {
    case event: FieldChanged => updated
    case event: GameStart => updated
  }

  def updated =  println(controller.playgroundToString)
}