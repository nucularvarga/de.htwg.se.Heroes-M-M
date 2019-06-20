package de.htwg.se.heroes.model

import de.htwg.se.heroes.model.fieldComponent.Stop
import org.scalatest.{Matchers, WordSpec}

class StopCellSpec extends WordSpec with Matchers {

  "A LeerCell" when {
    "not set to any value " should {
      val emptyCell = Stop()
      "have value 0" in {
        emptyCell.typ should be("X")
      }
    }
  }
}