package de.htwg.se.heroes.controllerComponent.controllerBaseImpl.gamemode

import de.htwg.se.heroes.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.heroes.controllerComponent.controllerBaseImpl.gamemode.UIEvent.UIEvent
import de.htwg.se.heroes.util.Command

class MapCommand(Map: MapMode, val e: UIEvent, controller: Controller) extends Command {

  /*bewegung auf der karte*/

  override def doStep: Unit =   controller.mode = controller.mode.handle(e)

  override def undoStep: Unit = {
    controller.mode = Map
  }
  override def redoStep: Unit = {
    controller.playerBase.nextPlayer
    controller.mode = controller.mode.handle(inverseEvent(e))
    controller.playerBase.nextPlayer
  }


  def inverseEvent(d: UIEvent): UIEvent = {
    d match {
      case UIEvent.MoveUp => UIEvent.MoveDown
      case UIEvent.MoveLeft => UIEvent.MoveRight
      case UIEvent.MoveDown => UIEvent.MoveUp
      case UIEvent.MoveRight => UIEvent.MoveLeft
    }
  }

}