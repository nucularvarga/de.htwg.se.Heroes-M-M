/*
package controller

import de.htwg.se.heroes.controller.Direction.Value
import de.htwg.se.heroes.controller.GameMode.Value
import de.htwg.se.heroes.model._
import de.htwg.se.heroes.controller.{Controller, Direction, GameMode}
import org.scalatest.{Matchers, WordSpec}

import scala.language.reflectiveCalls
import scala.collection.immutable.ListMap



class ControllerSpec extends WordSpec with Matchers {

  "A Controller" when {
    "controller in mapmode" should {
      val controller = new Controller(new Field(9), new Arena(10, 30))
      controller.init()
      "player check" in {
        controller.playerBase.playerBase(0) should be(Player("1", 100, 100, new ListMap[Cell, Int],  6, 6))
        controller.playerBase.playerBase(1) should be (Player("2", 100, 100,  new ListMap[Cell, Int], 3, 3))
      }
      "player stats" in {
        controller.showStats()
        controller.messanger.msg should be (controller.playerBase.playerBase(0).toString)
      }
      "move _ and stop enemy" in {
        var testcontroller = new Controller(new Field(3), new Arena(2,4))
        testcontroller.playerBase.addPlayer("1", 0, 10, new ListMap[Cell, Int], 0, 0)
        testcontroller.playField =  testcontroller.playField.set(0,1, Stop())
        testcontroller.playField =  testcontroller.playField.set(2,0, EnemyCell(1))
        testcontroller.playField =  testcontroller.playField.set(1,0, HeroCell("2"))
        testcontroller.action(Direction.Right)
        testcontroller.playField.cell(0,1).toString should be("X")
        testcontroller.action(Direction.Down)
        testcontroller.playField.cell(1,0) should be(HeroCell("2"))
        testcontroller.action(Direction.Down)
        testcontroller.playField.cell(2,0).toString should be("F")
      }
      "set gold und see stats" in {
        var test = new Controller(new Field(3), new Arena(2, 4))
        test.playerBase.addPlayer("1", 122, 10, new ListMap[Cell, Int], 0, 0)
        test.openShop(100)
        test.messanger.msg should be("Nicht genug gold")
        test.openShop(5)
        test.messanger.msg should be("Erfolgreich gekauft")
      }
        "start battle" in {
          var kampf = new Controller(new Field(3), new Arena(2,4))
          kampf.playerBase = kampf.playerBase.addPlayer("1", 122, 10, new ListMap[Cell, Int] , 0, 0)
          kampf.playerBase.playerBase = kampf.playerBase.playerBase.updated(0, kampf.playerBase.getPlayer.addUnit(Soldier(0,0), 5, 10))
          kampf.startBattle(EnemyCell(2))
          kampf.mode should be(GameMode.Combat)

      }
    }
  }
}*/
