package controller

import de.htwg.se.heroes.controllerComponent.ControllerInterface
import de.htwg.se.heroes.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.heroes.controllerComponent.controllerBaseImpl.gamemode.{MapCommand, MapMode, UIEvent}
import de.htwg.se.heroes.main.injector
import de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.heroes.model.playerComponent.playerListBaseImpl.{Player, PlayerList}
import de.htwg.se.heroes.model.soldier.soldierBaseImpl.Soldier
import org.scalatest.{Matchers, WordSpec}

import scala.collection.immutable.ListMap

class MapCommandSpec extends WordSpec with Matchers {
  "A MapCommandSpec" when {
    "ececuting inverseEvent" should {
      var playerBase = PlayerList(Vector.empty[Player], 0)
      playerBase = playerBase.addPlayer("1", 100, 100, new ListMap[Soldier, Int], 1, 1)
      playerBase = playerBase.addPlayer("2", 100, 100, new ListMap[Soldier, Int], 1, 1)

      val map = MapMode(new Field(9), playerBase)
      val controller = injector.getInstance(classOf[Controller])

      val up = UIEvent.MoveUp
      val down = UIEvent.MoveDown
      val left = UIEvent.MoveLeft
      val right = UIEvent.MoveRight

      val mapcom = new MapCommand(map, up, controller)

      "have value UIEvent.MoveDown" in {
        mapcom.inverseEvent(up) should be(UIEvent.MoveDown)
      }
      "have value UIEvent.MoveUp" in {
        mapcom.inverseEvent(down) should be(UIEvent.MoveUp)
      }
      "have value UIEvent.MoveRight" in {
        mapcom.inverseEvent(left) should be(UIEvent.MoveRight)
      }
      "have value UIEvent.MoveLeft" in {
        mapcom.inverseEvent(right) should be(UIEvent.MoveLeft)
      }
    }
  }
}
