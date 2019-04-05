package de.htwg.se.heroes.model

import org.scalatest.{Matchers, WordSpec}

class FieldSpec extends WordSpec with Matchers {

  "A Field" when {
    "not set to any value " should {
      val emptyCell = Cell(0)
      "have value 0" in {
        emptyCell.typ should be(0)
      }
    }
    "set to a specific value" should {
      val nonEmptyCell = Cell(0)
      "return that value" in {
        nonEmptyCell.typ should be(5)
      }
    }
  }

}