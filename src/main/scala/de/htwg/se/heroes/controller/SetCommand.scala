package de.htwg.se.heroes.controller

import de.htwg.se.heroes.model.HeroCell
import de.htwg.se.heroes.util.Command

class SetCommand(x:Int, y: Int, hero: HeroCell, controller: Controller) extends Command {

  /*bewegung auf der karte*/

  override def doStep: Unit =   controller.playField = controller.playField.set(x, y, hero)

  override def undoStep: Unit = controller.playField = controller.playField.set(x, y, hero)

  override def redoStep: Unit = controller.grid = controller.grid.set(row, col, value)
}