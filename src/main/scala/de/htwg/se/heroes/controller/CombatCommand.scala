package de.htwg.se.heroes.controller

import de.htwg.se.heroes.model.HeroCell
import de.htwg.se.heroes.util.Command

class CombatCommand(Combat: GameMode, controller: Controller) extends Command {

  /*bewegung auf dem schlachtfeld*/

  override def doStep: Unit =   controller.mode = Combat

  override def undoStep: Unit = controller.mode = Combat

  override def redoStep: Unit = controller.mode = Combat
}