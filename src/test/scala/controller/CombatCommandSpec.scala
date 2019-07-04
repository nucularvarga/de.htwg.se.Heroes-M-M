package controller

import de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl.Cell
import org.scalatest.{Matchers, WordSpec}

class CombatCommandSpec extends WordSpec with Matchers{
  "A CombatCommandSpec" when {
    "an empty Cell " should {
      val emptyCell = new Cell()
      "have value ' '" in {
        emptyCell.typ should be(" ")
        emptyCell.toString should be(" ")
      }
    }
  }
}
