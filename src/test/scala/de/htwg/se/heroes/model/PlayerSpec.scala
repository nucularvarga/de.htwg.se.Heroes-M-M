package de.htwg.se.heroes.model

import org.scalatest.{Matchers, WordSpec}

import scala.collection.immutable.ListMap

class PlayerSpec extends WordSpec with Matchers {

  "A Player" when {
    "not set to any value " should {
      val emptyPlayer = Player("", 0 , 0, UnitOrder(new ListMap[Soldier, Int]), 0, 0)
      "have value '' and 0" in {
        emptyPlayer.name should be("")
        emptyPlayer.gold should be(0)
        emptyPlayer.strength should be(0)
        emptyPlayer.x should be(0)
        emptyPlayer.y should be(0)
      }
    }
    "set to a specific values" should {
      var nonEmptyPlayer = Player("test", 100, 10, UnitOrder(new ListMap[Soldier, Int]), 1, 1)
      "return the values" in {
        nonEmptyPlayer.name should be("test")
        nonEmptyPlayer.gold should be(100)
        nonEmptyPlayer.strength should be(10)
      }
      "player walks right" in {
        nonEmptyPlayer.walk(1, 0) should be(Player("test", 100, 10, UnitOrder(new ListMap[Soldier, Int]), 2, 1))
      }
      "player grows stronger" in {
        nonEmptyPlayer.powerUp(200) should be(Player("test", 100, 210, UnitOrder(new ListMap[Soldier, Int]), 1, 1))
      }
      "player adds units" in {
        val list = UnitOrder(new ListMap[Soldier, Int])
        val tmp = nonEmptyPlayer.units.addUnit(Soldier(1,1), 1)
        nonEmptyPlayer = nonEmptyPlayer.unit(tmp)
        nonEmptyPlayer.units should be(list.addUnit(Soldier(1,1),1))
      }
      "player adds again units" in {
        val list = UnitOrder(new ListMap[Soldier, Int])
        val tmp = nonEmptyPlayer.units.addUnit(Soldier(1,1), 1)
        nonEmptyPlayer = nonEmptyPlayer.unit(tmp)
        nonEmptyPlayer.units should be(list.addUnit(Soldier(1,1), 2))
        nonEmptyPlayer.toString should be("test: Gold: 90 Strength: 10 Units: UnitOrder(ListMap(S -> 2))")
      }
    }
  }
}
