/*
package controller

import de.htwg.se.heroes.controllerComponent.Controller
import de.htwg.se.heroes.controllerComponent.gamemode.{CombatCommand, CombatMode, MapMode, UIEvent}
import de.htwg.se.heroes.model.fieldComponent.{Arena, Field}
import de.htwg.se.heroes.model.fieldComponent.{Arena, EnemyCell, Field}
import de.htwg.se.heroes.model.playerComponent.{Player, PlayerList, Soldier}
import org.scalatest.{Matchers, WordSpec}

import scala.collection.immutable.ListMap

class CombatCommandSpec extends WordSpec with Matchers {

  "A Arena" when {
    val controller = new Controller(new Field(9), new Arena(5, 10))
    controller.playerBase = PlayerList(Vector.empty[Player], 0)
    controller.playerBase =  controller.playerBase.addPlayer("1", 100, 100, new ListMap[Soldier, Int], 1, 1)
    controller.playerBase =  controller.playerBase.addPlayer("2", 100, 100, new ListMap[Soldier, Int], 1, 1)

    controller.playerBase =  controller.playerBase.setUnits(5, 5)
    var enemy = EnemyCell(1)
    "set to a specific value" should {
      var enemy = EnemyCell(1)
      val command = new CombatCommand(CombatMode(new Arena(30, 10),  controller.playerBase, enemy, MapMode(new Field(9),  controller.playerBase)), UIEvent.MoveDown, controller)

      "setup the bloody arena" in {
        command.doStep should be(CombatMode)
      }
    }
  }
}
*/