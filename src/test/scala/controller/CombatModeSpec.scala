
package controller


import de.htwg.se.heroes.controller.{CombatMode, Event}
import de.htwg.se.heroes.model._
import org.scalatest.{Matchers, WordSpec}

import scala.collection.immutable.ListMap

class CombatModeSpec extends WordSpec with Matchers {

  "A Arena" when {
    "set to a specific value" should {
      var playerBase = new PlayerList
      playerBase = playerBase.addPlayer("1", 100, 100,UnitOrder(new ListMap[Soldier, Int]),  1, 1)
      playerBase = playerBase.addPlayer("2", 100, 100,  UnitOrder(new ListMap[Soldier, Int]), 1, 1)

      playerBase = playerBase.setUnits(5, 5)

      var enemy = EnemyCell(1)
      val combat = CombatMode(new Arena(30, 10), playerBase, enemy)
      "setup the bloody arena" in {
        combat.handle(Event.StartCombat).toString should be(combat.playArena.toString)
      }
      "move gladiator" in {
        val moved = combat.move(Event.MoveRight)
        moved.asInstanceOf[CombatMode].playArena.cell(2,1).toString should be("S")
      }
      "action left" in {
        val moved = combat.handle(Event.MoveLeft)
        moved.asInstanceOf[CombatMode].playArena.cell(1,1).toString should be("S")
      }
      "action top" in {
        val moved = combat.handle(Event.MoveUp)
        moved.asInstanceOf[CombatMode].playArena.cell(1,1).toString should be("S")
      }
    }

  }
}