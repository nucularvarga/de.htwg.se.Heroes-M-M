package aview

import de.htwg.se.heroes.aview.{JFXGui, Tui}
import de.htwg.se.heroes.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.heroes.controllerComponent.controllerBaseImpl.gamemode.MapMode
import de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl.{Arena, EnemyCell, Field, HeroCell}
import org.scalatest.{Matchers, WordSpec}

class GUISpec extends WordSpec with Matchers{

  "A Heroes Tui" should {
    val controller = new Controller(new Field(9), new Arena(5, 10))
    val gui = new JFXGui(controller)
    "create and empty playground on input 'n'" in {
      //gui.drawScene
      controller.playField.toString should be(new Field(9).toString)
    }
    "init playground on input 'i'" in {

      var filed = new Field(9)

      filed = filed.initField
      filed = filed.set(6, 6, HeroCell("1"))
      filed = filed.set(3, 3, HeroCell("2"))
      filed = filed.set(3, 7, EnemyCell(2))
      //controller.playField.toString should be(filed.toString)
    }

    "move 1 on input 'w'" in {
      //controller.playField.cell(5,6) should be(HeroCell("1"))
      //controller.mode.asInstanceOf[MapMode].playField.cell(5, 5) should be(HeroCell("1"))
    }

  }
}
