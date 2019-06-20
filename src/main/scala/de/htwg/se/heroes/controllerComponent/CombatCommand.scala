package de.htwg.se.heroes.controllerComponent


import de.htwg.se.heroes.controllerComponent.UIEvent.UIEvent
import de.htwg.se.heroes.util.Command

class CombatCommand(Combat: GameMode, e: UIEvent, controller: Controller) extends Command {

  /*bewegung auf dem schlachtfeld*/

  override def doStep: Unit = controller.mode = controller.mode.handle(e)

  override def undoStep: Unit = controller.mode = Combat

  override def redoStep: Unit = controller.mode = Combat
}