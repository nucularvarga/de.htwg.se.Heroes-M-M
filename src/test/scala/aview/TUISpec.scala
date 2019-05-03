package aview
import de.htwg.se.heroes.controller.Controller
import de.htwg.se.heroes.model._
import de.htwg.se.heroes.aview._

import org.scalatest.{Matchers, WordSpec}

class TUISpec  extends WordSpec with Matchers{

  "A Heroes Tui" should {
    val controller = new Controller(new Field(1))
    val tui = new Tui(controller)
    "create and empty playground on input 'n'" in {
      tui.processInputLine("n")
      controller.playground.toString should be(new Field(9).toString)
    }
    "init playground on input 'i'" in {
      tui.processInputLine("i")
      var filed = new Field(9)

      filed = filed.initField
      filed = filed.replaceCell(6, 6, HeroCell("1"))
      filed = filed.replaceCell(3, 3, HeroCell("2"))
      controller.playground.toString should be(filed.toString)
    }
    /*
    "move 1 on input 'w'" in {
      tui.processInputLine("w")
      controller.playground.cell(5,6) should be(HeroCell("1"))
    }
    "create a random heroes on input 's'" in {
      tui.processInputLine("s")
      controller.playground.cell(4,3) should be(HeroCell("2"))
    }
    "create a random heroes on input 'a'" in {
      tui.processInputLine("a")
      controller.playground.cell(5, 5) should be(HeroCell("1"))
    }
    "create a random heroes on input 'd'" in {
      tui.processInputLine("d")
      controller.playground.cell(4, 4) should be(HeroCell("2"))
    }
    "create a random heroes on input 'Any'" in {
      tui.processInputLine("x")
      controller.playground.cell(4, 4) should be(HeroCell("2"))
    }
    */
  }
}