package de.htwg.se.heroes.model

import de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl.Leer
import org.scalatest.{Matchers, WordSpec}

class LeerSpec extends WordSpec with Matchers {

  "A LeerCell" when {
    "not set to any value " should {
      val emptyCell = Leer()
      "have value ' '" in {
        emptyCell.typ should be(" ")
      }
    }
  }

}