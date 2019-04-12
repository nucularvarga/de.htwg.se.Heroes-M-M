package de.htwg.se.heroes.model

import org.scalatest.{Matchers, WordSpec}

class HeroCellSpec extends WordSpec with Matchers {

  "A LeerCell" when {
    "not set to any value " should {
      val emptyCell = HeroCell()
      "have value 0" in {
        emptyCell.typ should be("H")
      }
    }
  }

}