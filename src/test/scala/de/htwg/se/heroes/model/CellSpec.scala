package de.htwg.se.heroes.model

import org.scalatest.{Matchers, WordSpec}

class CellSpec extends WordSpec with Matchers {

  "A Cell" when {
    "not set to any value " should {
      val emptyCell = new Cell()
      "have value 0" in {
        emptyCell.typ should be(" ")
      }
    }
    "set to a specific value" should {
      val nonEmptyCell = new Cell()
      "return that value" in {
        nonEmptyCell.typ should be(" ")
      }
    }
  }

}