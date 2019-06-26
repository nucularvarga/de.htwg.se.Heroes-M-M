package util

import de.htwg.se.heroes.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.heroes.controllerComponent.controllerBaseImpl.gamemode.{MapCommand, MapMode, UIEvent}
import de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl.{Arena, Field}
import de.htwg.se.heroes.model.playerComponent.playerListBaseImpl.{Player, PlayerList}
import de.htwg.se.heroes.util.UndoManager
import org.scalatest.{Matchers, WordSpec}

class UndoManagerSpec extends WordSpec with Matchers {

  "An UndoManager" should {
    val undoManager = new UndoManager

    "have a do, undo and redo" in {
      val mode = MapMode(new Field(9), PlayerList(Vector.empty[Player],0))
      val command = new MapCommand(mode, UIEvent.MoveUp, new Controller(new Field(9), new Arena(8, 20)))
      command.e should be(UIEvent.MoveUp)
      //undoManager.doStep(command)
    }
  }
}