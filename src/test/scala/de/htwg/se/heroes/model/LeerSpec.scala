package de.htwg.se.heroes.model

import org.scalatest.{Matchers, WordSpec}

class LeerSpec extends WordSpec with Matchers {

  "A LeerCell" when {
    "not set to any value " should {
      val emptyCell = Leer()
      "have value 0" in {
        emptyCell.typ should be(" ")
      }
    }
  }

}