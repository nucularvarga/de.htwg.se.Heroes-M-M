package de.htwg.se.heroes.model

import org.scalatest.{Matchers, WordSpec}

class EnemyCellSpec extends WordSpec with Matchers {

  "An EnemyCell" when {
    "not set to any value " should {
      val emptyCell = EnemyCell(0)
      "have value '' and 0" in {
        emptyCell.typ should be("F")
        emptyCell.toString should be("F")
        emptyCell.strength should be(0)
      }
    }
    "set to a specific value" should {
      val nonEmptyCell = EnemyCell(5)
      "has strength = 5" in {
        nonEmptyCell.typ should be("F")
        nonEmptyCell.toString should be("F")
        nonEmptyCell.strength should be(5)
      }
    }
  }
}
