package de.htwg.se.heroes.model

import org.scalatest.{Matchers, WordSpec}

import scala.collection.immutable.ListMap

class PlayerListSpec extends WordSpec with Matchers {

  "A PlayerList" when {
    "add player " should {
      var emptyPlayerList = new PlayerList
      "have value '' and 0" in {
        emptyPlayerList.addPlayer("janko", 0, 0, new ListMap[Soldier, Int], 1, 1)
        emptyPlayerList.getPlayer should be(Player("janko", 0, 0, new ListMap[Soldier, Int], 1, 1))
      }
      "next player" in {
        emptyPlayerList.nextPlayer
        emptyPlayerList = emptyPlayerList.updatePlayer(1, 1, 1)
        emptyPlayerList.PlayerTurn should be(0)
      }
    }
  }
}
