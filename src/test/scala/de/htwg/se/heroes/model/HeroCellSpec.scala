package de.htwg.se.heroes.model

import org.scalatest.{Matchers, WordSpec}

class HeroCellSpec extends WordSpec with Matchers {

  "A LeerCell" when {
    "not set to any value " should {
      val playerCell = HeroCell("1")
      "have value 0" in {
        playerCell.typ should be("1")
      }
    }
  }

}