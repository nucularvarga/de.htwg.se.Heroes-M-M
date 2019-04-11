package de.htwg.se.heroes.model

import org.scalatest.{Matchers, WordSpec}

class CellSpec extends WordSpec with Matchers {

  "A Cell" when {
    "not set to any value " should {
      val emptyCell = new Cell()
      "have value 0" in {
        emptyCell.typ should be(" ")
        emptyCell.toString should be(" ")
      }
    }
  }

}