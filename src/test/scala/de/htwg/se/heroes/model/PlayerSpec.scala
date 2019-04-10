package de.htwg.se.heroes.model

import org.scalatest.{Matchers, WordSpec}

class PlayerSpec extends WordSpec with Matchers {

  "A Player" when {
    "not set to any value " should {
      val emptyPlayer = new Player("", 0 , 0, 1, 1)
      "have value '' and 0" in {
        emptyPlayer.name should be("")
        emptyPlayer.gold should be(0)
        emptyPlayer.strength should be(0)
      }
    }
    "set to a specific values" should {
      val nonEmptyPlayer = new Player("test", 100, 10, 1, 1)
      "return the values" in {
        nonEmptyPlayer.name should be("test")
        nonEmptyPlayer.gold should be(100)
        nonEmptyPlayer.strength should be(10)
      }
    }
  }
}
