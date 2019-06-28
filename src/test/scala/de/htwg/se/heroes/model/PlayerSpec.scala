package de.htwg.se.heroes.model


import de.htwg.se.heroes.model.playerComponent.playerListBaseImpl.Player
import de.htwg.se.heroes.model.soldier.SoldierInterface
import de.htwg.se.heroes.model.soldier.soldierBaseImpl.Soldier
import org.scalatest.{Matchers, WordSpec}

import scala.collection.immutable.ListMap

class PlayerSpec extends WordSpec with Matchers {

  "A Player" when {
    "not set to any value " should {
      val emptyPlayer = Player("", 0 , 0, new ListMap[SoldierInterface, Int], 0, 0)
      "have value '' and 0" in {
        emptyPlayer.name should be("")
        emptyPlayer.gold should be(0)
        emptyPlayer.strength should be(0)
        emptyPlayer.x should be(0)
        emptyPlayer.y should be(0)
      }
    }
    "set to a specific values" should {
      var nonEmptyPlayer = Player("test", 100, 10, new ListMap[SoldierInterface, Int], 1, 1)
      "return the values" in {
        nonEmptyPlayer.name should be("test")
        nonEmptyPlayer.gold should be(100)
        nonEmptyPlayer.strength should be(10)
      }
      "player walks right" in {
        nonEmptyPlayer.walk(1, 0) should be(Player("test", 100, 10, new ListMap[SoldierInterface, Int], 2, 1))
      }
      "player grows stronger" in {
        nonEmptyPlayer.powerUp(200) should be(Player("test", 100, 210, new ListMap[SoldierInterface, Int], 1, 1))
      }
      "player adds units" in {
        val list = new ListMap[Soldier, Int]()
        nonEmptyPlayer = nonEmptyPlayer.addUnit(Soldier(1,1), 1, 5)
        nonEmptyPlayer.units should be(list.updated(Soldier(1,1), 1))
      }
      "player adds again units" in {
        val list = new ListMap[Soldier, Int]()
        nonEmptyPlayer = nonEmptyPlayer.addUnit(Soldier(1,1), 1, 5)
        nonEmptyPlayer.units should be(list.updated(Soldier(1,1), 2))
        nonEmptyPlayer.toString should be("test: Gold: 90 Strength: 10 Units: ListMap(S -> 2)")
      }
    }
  }
}
