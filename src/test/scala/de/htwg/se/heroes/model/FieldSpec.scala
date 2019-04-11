package de.htwg.se.heroes.model

import org.scalatest.{Matchers, WordSpec}

class FieldSpec extends WordSpec with Matchers {

  "A Field" when {
    "not set to any value " should {
      val emptyField = new Field(0)
      "have size 0" in {
        emptyField.rows.size should be(0)
      }
    }
    "set to a specific value" should {
      val nonEmptyField = new Field(5)
      "return that value" in {
        nonEmptyField.rows.size should be(5)
        nonEmptyField.rows(0).size should be(5)
      }
    }
  }

}