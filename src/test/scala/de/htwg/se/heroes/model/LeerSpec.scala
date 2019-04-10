package de.htwg.se.heroes.model

import org.scalatest.{Matchers, WordSpec}

class LeerSpec extends WordSpec with Matchers {

  "A Cell" when {
    "not set to any value " should {
      val emptyCell = Leer()
      val a: Cell = emptyCell
      "have value 0" in {
        a.typ should be("X")
      }
    }
    "set to a specific value" should {
      val emptyCell = Leer()
      val a: Cell = emptyCell
      "have value 0" in {
        a.typ should be("X")
      }
    }
  }

}