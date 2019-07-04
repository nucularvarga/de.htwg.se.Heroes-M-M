package aview

import de.htwg.se.heroes.aview.JFXGui
import de.htwg.se.heroes.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl._
import org.scalatest.{Matchers, WordSpec}
import scalafx.event
import scalafx.event.ActionEvent

class GUISpec  extends WordSpec with Matchers {

  "A Heroes Gui" should {
    val controller = new Controller(new Field(9), new Arena(5, 10))
    val gui = new JFXGui(controller)
    new Thread(new Runnable {
      def run(): Unit = {
        gui.main(Array())
      }
    }).start()
    "create and empty playground on input 'n'" in {
      gui.drawView should be()
    }
  }
}
