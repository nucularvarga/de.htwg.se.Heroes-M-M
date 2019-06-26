package controller

import de.htwg.se.heroes.controllerComponent.controllerBaseImpl.gamemode.{CombatMode, MapMode}
import de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl.{EnemyCell, Field}
import de.htwg.se.heroes.model.playerComponent.playerListBaseImpl.{Player, PlayerList}
import de.htwg.se.heroes.model.soldier.soldierBaseImpl.{MeleeSoldier, Soldier}
import org.scalatest.{Matchers, WordSpec}

import scala.collection.immutable.ListMap

class MapModeSpec extends WordSpec with Matchers {
  "A MapMode" when {
    "set to a specific value" should {
      var playerBase = PlayerList(Vector.empty[Player],0)
      playerBase = playerBase.addPlayer("1", 100, 100, new ListMap[Soldier, Int], 1, 1)
      playerBase = playerBase.addPlayer("2", 100, 100, new ListMap[Soldier, Int], 1, 1)

      playerBase = playerBase.setUnits(new MeleeSoldier(4,4), 5, 5)

      var enemy = EnemyCell(1)
      val map = MapMode(new Field(9), playerBase)
      "setup the bloody arena" in {
        map.startBattle(enemy).isInstanceOf[CombatMode] should be(true)
        //map.handle(Event.StartCombat).isInstanceOf[CombatMode] should be(true)
      }
    }
  }
}