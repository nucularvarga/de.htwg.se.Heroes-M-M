
package controller


import de.htwg.se.heroes.controller.{CombatMode, Event}
import de.htwg.se.heroes.model._
import org.scalatest.{Matchers, WordSpec}

import scala.collection.immutable.ListMap

class CombatModeSpec extends WordSpec with Matchers {

  "A Arena" when {
    "set to a specific value" should {
      var playerBase = PlayerList(Vector.empty[Player],0)
      playerBase = playerBase.addPlayer("1", 100, 100, new ListMap[Soldier, Int],  1, 1)
      playerBase = playerBase.addPlayer("2", 100, 100,  new ListMap[Soldier, Int], 1, 1)

      playerBase = playerBase.setUnits(5, 5)

      var enemy = EnemyCell(1)
      val comb = CombatMode(new Arena(30, 10), playerBase, enemy)
      comb.initArena()
      "setup the bloody arena" in {
        val combat = CombatMode(new Arena(30, 10), playerBase, enemy)
        combat.handle(Event.StartCombat).toString should be(comb.initArena().toString)
      }
      "move gladiator" in {
        val combat = CombatMode(new Arena(30, 10), playerBase, enemy)
        val moved = combat.move(Event.MoveRight)
        moved.playArena.cell(2,1).toString should be("S")
      }
      "action left" in {
        val combat = CombatMode(new Arena(30, 10), playerBase, enemy)
        val moved = combat.action(Event.MoveUp)
        moved.asInstanceOf[CombatMode].playArena.cell(1,0).toString should be("S")
      }
      "action top" in {
        val combat = CombatMode(new Arena(30, 10), playerBase, enemy)
        val moved = combat.move(Event.MoveDown)
        moved.playArena.cell(1,2).toString should be("S")
      }
      "action blocked" in {
        val combat = CombatMode(new Arena(30, 10), playerBase, enemy)
        var moved = combat.initArena()
        moved = moved.action(Event.MoveUp).asInstanceOf[CombatMode]
        moved.playArena.cell(1,1).toString should be("S")
      }
    }

  }
}