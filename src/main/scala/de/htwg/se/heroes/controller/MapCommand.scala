package de.htwg.se.heroes.controller

import de.htwg.se.heroes.controller.Event.Event
import de.htwg.se.heroes.model.HeroCell
import de.htwg.se.heroes.util.Command

class MapCommand(Map: MapMode, e: Event, controller: Controller) extends Command {

  /*bewegung auf der karte*/

  override def doStep: Unit =   controller.mode = controller.mode.handle(e)

  override def undoStep: Unit = {
    controller.playerBase.nextPlayer
    controller.mode = controller.mode.handle(inverseEvent(e))
    controller.playerBase.nextPlayer
  }
  override def redoStep: Unit = controller.mode = Map


  def inverseEvent(d: Event): Event = {
    d match {
      case Event.MoveUp => Event.MoveDown
      case Event.MoveLeft => Event.MoveRight
      case Event.MoveDown => Event.MoveUp
      case Event.MoveRight => Event.MoveLeft
    }
  }

}