package de.htwg.se.heroes.aview

import de.htwg.se.heroes.controllerComponent._
import de.htwg.se.heroes.controllerComponent.controllerBaseImpl.gamemode.UIEvent

import scala.io.StdIn.readLine
import scala.swing.Reactor

class Tui(controller: ControllerInterface) extends Reactor {

  listenTo(controller)



  def processInputLine(input: String):Unit = {
    /*
    val inputsplit = input.split(",")
    var typ = UIEvent.BuyMelee
    if(inputsplit.length >= 2) {
      typ = inputsplit(2) match {
        case "m" => UIEvent.BuyMelee
        case "r" => UIEvent.BuyRange
        case _ => UIEvent.BuyMelee
    }
    }
*/
   // val pattern = "([a-z]) ([0-9]+)".r
   // val pattern(cmd, number) = input

    input match {
      case "q" =>
      // TODO case "add" => addspieler(name)
      case "n" => controller.createNewField(9)
      case "i" => println("Wie viel spieler?"); controller.init(readLine())
      case "r" => controller.undo
      case "y" => controller.redo
      case "k" => controller.save
      case "l" => controller.load
      case "w" => controller.action(UIEvent.MoveUp)
      case "a" => controller.action(UIEvent.MoveLeft)
      case "s" => controller.action(UIEvent.MoveDown)
      case "d" => controller.action(UIEvent.MoveRight)
      case "z" => controller.show(UIEvent.MoveUp)
      case "g" => controller.show(UIEvent.MoveLeft)
      case "h" => controller.show(UIEvent.MoveDown)
      case "j" => controller.show(UIEvent.MoveRight)
      case "t" => controller.showStats()
      //case "b" => controller.openShop(typ, inputsplit(1).toInt)
      case _   =>
    }
  }

  reactions += {
    case event: FieldChanged => updated
    case event: GameStart => updated
    case event: ViewChanged => lookAround
  }

  def lookAround = println(controller.viewToString)

  def updated =  println(controller.playgroundToString)
}