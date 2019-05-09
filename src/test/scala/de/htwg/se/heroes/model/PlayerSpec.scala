package de.htwg.se.heroes.model

import org.scalatest.{Matchers, WordSpec}

import scala.collection.immutable.ListMap

class PlayerSpec extends WordSpec with Matchers {

  "A Player" when {
    "not set to any value " should {
      val emptyPlayer = Player("", 0 , 0, new ListMap[Cell, Int], 0, 0)
      "have value '' and 0" in {
        emptyPlayer.name should be("")
        emptyPlayer.gold should be(0)
        emptyPlayer.strength should be(0)
        emptyPlayer.x should be(0)
        emptyPlayer.y should be(0)
      }
    }
    "set to a specific values" should {
      val nonEmptyPlayer = Player("test", 100, 10, new ListMap[Cell, Int], 1, 1)
      "return the values" in {
        nonEmptyPlayer.name should be("test")
        nonEmptyPlayer.gold should be(100)
        nonEmptyPlayer.strength should be(10)
      }
      "player walks right" in {
        nonEmptyPlayer.walk(1, 0) should be(Player("test", 100, 10, new ListMap[Cell, Int], 2, 1))
      }
      "player grows stronger" in {
        nonEmptyPlayer.powerUp(200) should be(Player("test", 100, 210, new ListMap[Cell, Int], 1, 1))
      }
    }
  }
}
