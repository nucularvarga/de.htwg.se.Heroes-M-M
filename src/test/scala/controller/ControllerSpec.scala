package controller

import de.htwg.se.heroes.controller.Direction.Value
import de.htwg.se.heroes.controller.GameMode.Value
import de.htwg.se.heroes.model._
import de.htwg.se.heroes.controller.Controller
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
    }
  }
}