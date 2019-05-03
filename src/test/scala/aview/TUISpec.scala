package aview
import de.htwg.se.heroes.controller.Controller
import de.htwg.se.heroes.model._
import de.htwg.se.heroes.aview._

import org.scalatest.{Matchers, WordSpec}

class TUISpec  extends WordSpec with Matchers{

  "A Heroes Tui" should {
    val controller = new Controller(new Field(1), new Arena(5, 10))
    val tui = new Tui(controller)
    "create and empty playground on input 'n'" in {
      tui.processInputLine("n")
      controller.playField.toString should be(new Field(9).toString)
    }
    "init playground on input 'i'" in {
      tui.processInputLine("i")
      var filed = new Field(9)

      filed = filed.initField
      filed = filed.set(6, 6, HeroCell("1"))
      filed = filed.set(3, 3, HeroCell("2"))
      controller.playField.toString should be(filed.toString)
    }

    "move 1 on input 'w'" in {
      tui.processInputLine("w")
      controller.playField.cell(5,6) should be(HeroCell("1"))
    }

    "create a random heroes on input 's'" in {
      tui.processInputLine("s")
      controller.playField.cell(4,3) should be(HeroCell("2"))
    }
    "create a random heroes on input 'a'" in {
      tui.processInputLine("a")
      controller.playField.cell(5, 5) should be(HeroCell("1"))
    }
    "create a random heroes on input 'd'" in {
      tui.processInputLine("d")
      controller.playField.cell(4, 4) should be(HeroCell("2"))
    }
    "create a random heroes on input 'Any'" in {
      tui.processInputLine("x")
      controller.playField.cell(4, 4) should be(HeroCell("2"))
    }

  }
}