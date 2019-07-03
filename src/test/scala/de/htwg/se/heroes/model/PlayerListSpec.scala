package de.htwg.se.heroes.model


import de.htwg.se.heroes.model.playerComponent.playerListBaseImpl.{Player, PlayerList}
import de.htwg.se.heroes.model.soldier.SoldierInterface
import de.htwg.se.heroes.model.soldier.soldierBaseImpl.Soldier
import org.scalatest.{Matchers, WordSpec}

import scala.collection.immutable.ListMap

class PlayerListSpec extends WordSpec with Matchers {

  "A PlayerList" when {
    "add player " should {
      var emptyPlayerList = PlayerList(Vector.empty[Player],0)
      "have value '' and 0" in {
        emptyPlayerList = emptyPlayerList.addPlayer("janko", 0, 0, new ListMap[Soldier, Int], 1, 1)
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
