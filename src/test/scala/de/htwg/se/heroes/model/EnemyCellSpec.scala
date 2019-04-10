package de.htwg.se.heroes.model

import org.scalatest.{Matchers, WordSpec}

class EnemyCellSpec extends WordSpec with Matchers {

  "An EnemyCell" when {
    "not set to any value " should {
      val emptyCell = EnemyCell("", 0)
      "have value '' and 0" in {
        emptyCell.typ should be("")
        emptyCell.strength should be(0)
      }
    }
    "set to a specific value" should {
      val nonEmptyCell = EnemyCell("test", 5)
      "return that value" in {
        nonEmptyCell.typ should be("test")
        nonEmptyCell.strength should be(5)
      }
    }
  }
}
