package aview

import de.htwg.se.heroes.aview._
import de.htwg.se.heroes.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.heroes.controllerComponent.controllerBaseImpl.gamemode.MapMode
import de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl.{Arena, EnemyCell, Field, HeroCell, GoldCell}
import de.htwg.se.heroes.model.soldier.SoldierInterface
import de.htwg.se.heroes.model.soldier.soldierBaseImpl.Soldier
import org.scalatest.{Matchers, WordSpec}

import scala.collection.immutable.ListMap

class TUISpec  extends WordSpec with Matchers{

  "A Heroes Tui" should {
    val controller = new Controller(new Field(9), new Arena(5, 10))
    val tui = new Tui(controller)
    "create and empty playground on input 'n'" in {
      tui.processInputLine("n")
      controller.playField.toString should be(new Field(20).toString)
    }
    "init playground on input 'i2'" in {
      tui.processInputLine("i2")
      var filed = new Field(20)

      filed = filed.initField
      filed = filed.set(6, 6, HeroCell("1"))
      filed = filed.set(8, 8, HeroCell("2"))
      filed = filed.set(11, 9, GoldCell())
      filed = filed.set(16, 16, GoldCell())
      filed = filed.set(3, 2, GoldCell())
      filed = filed.set(15, 3, GoldCell())
      filed = filed.set(4, 15, GoldCell())
      filed = filed.set(17, 8, EnemyCell(100))
      filed = filed.set(3, 7, EnemyCell(20))
      filed = filed.set(11, 3, EnemyCell(50))
      filed = filed.set(10, 14, EnemyCell(100))
      controller.playField.toString should be(filed.toString)
    }

    "move 1 on input 'w'" in {
      tui.processInputLine("w")
      //controller.playField.cell(5,6) should be(HeroCell("1"))
      controller.mode.asInstanceOf[MapMode].playField.cell(6,5) should be(HeroCell("1"))
    }

    "create a random heroes on input 's'" in {
      tui.processInputLine("s")
      controller.mode.asInstanceOf[MapMode].playField.cell(8,9) should be(HeroCell("2"))
    }
    "create a random heroes on input 'a'" in {
      tui.processInputLine("a")
      controller.mode.asInstanceOf[MapMode].playField.cell(5, 5) should be(HeroCell("1"))
    }
    "create a random heroes on input 'd'" in {
      tui.processInputLine("d")
      controller.mode.asInstanceOf[MapMode].playField.cell(9, 9) should be(HeroCell("2"))
    }
    "create a random heroes on input 'Any'" in {
      tui.processInputLine("x")
      controller.mode.asInstanceOf[MapMode].playField.cell(9, 9) should be(HeroCell("2"))
    }
    "quit" in {
      tui.processInputLine("q")
    }
    "irgendwie t" in {
      tui.processInputLine("t")
    }
    "player buys units" in {
      controller.playerBase.addPlayer("2", 100, 100, new ListMap[Soldier, Int], 0, 0)
      tui.processInputLine("b,1,m")
      controller.mode.playlist.getPlayer.gold should be(90)
      controller.mode.playlist.getPlayer.units.toString should be("ListMap(M -> 1)")
    }

  }
}