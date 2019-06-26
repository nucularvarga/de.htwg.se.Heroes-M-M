
package controller


import de.htwg.se.heroes.controllerComponent.controllerBaseImpl.gamemode.{CombatMode, MapMode, UIEvent}
import de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl.{Arena, EnemyCell, Field}
import de.htwg.se.heroes.model.playerComponent.playerListBaseImpl.{Player, PlayerList}
import de.htwg.se.heroes.model.soldier.soldierBaseImpl.{MeleeSoldier, Soldier}
import org.scalatest.{Matchers, WordSpec}

import scala.collection.immutable.ListMap

class CombatModeSpec extends WordSpec with Matchers {

  "A Arena" when {
    "set to a specific value" should {
      var playerBase = PlayerList(Vector.empty[Player],0)
      playerBase = playerBase.addPlayer("1", 100, 100, new ListMap[Soldier, Int],  1, 1)
      playerBase = playerBase.addPlayer("2", 100, 100,  new ListMap[Soldier, Int], 1, 1)

      playerBase = playerBase.setUnits(new MeleeSoldier(4,4),5, 5)

      var enemy = EnemyCell(1)
      val comb = CombatMode(new Arena(30, 10), playerBase, enemy, MapMode(new Field(9), playerBase))
      comb.initArena()
      "setup the bloody arena" in {
        val combat = CombatMode(new Arena(30, 10), playerBase, enemy, MapMode(new Field(9), playerBase))
        combat.handle(UIEvent.StartCombat).toString should be(comb.initArena().toString)
      }
      "move gladiator" in {
        val combat = CombatMode(new Arena(30, 10), playerBase, enemy, MapMode(new Field(9), playerBase))
        val moved = combat.move(UIEvent.MoveRight)
        moved.playArena.cell(2,1).toString should be("S")
      }
      "action left" in {
        val combat = CombatMode(new Arena(30, 10), playerBase, enemy, MapMode(new Field(9), playerBase))
        val moved = combat.action(UIEvent.MoveUp)
        moved.asInstanceOf[CombatMode].playArena.cell(1,0).toString should be("S")
      }
      "action top" in {
        val combat = CombatMode(new Arena(30, 10), playerBase, enemy, MapMode(new Field(9), playerBase))
        val moved = combat.move(UIEvent.MoveDown)
        moved.playArena.cell(1,2).toString should be("S")
      }
      "action blocked" in {
        val combat = CombatMode(new Arena(30, 10), playerBase, enemy, MapMode(new Field(9), playerBase))
        var moved = combat.initArena()
        moved = moved.action(UIEvent.MoveUp).asInstanceOf[CombatMode]
        moved.playArena.cell(1,1).toString should be("S")
      }
    }

  }
}