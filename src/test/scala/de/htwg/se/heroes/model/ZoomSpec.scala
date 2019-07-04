package de.htwg.se.heroes.model

import de.htwg.se.heroes.controllerComponent.controllerBaseImpl.gamemode.UIEvent
import de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl.Matrix
import de.htwg.se.heroes.model.zoomComponent.zoomBaseImpl.Zoom
import org.scalatest.{Matchers, WordSpec}

class ZoomSpec extends WordSpec with Matchers {

  "A Zoom" when {
    "a init zoomer " should {
      var matrix = new Matrix(20)
      val zoomer = Zoom(8,8, matrix)
      "getMatrix" in {
        zoomer.getMatrix  should be(matrix)
      }
      "updateMatrix" in {
        val newMatrix = new Matrix(21)
        val newZoomer = Zoom(8,8,newMatrix)
        zoomer.updateMatrix(newMatrix) should be(newZoomer)
      }
      "rand erkennung unten" in {
        val n_matrix = new Matrix(9)
        zoomer.show(UIEvent.MoveDown) should be(Zoom(8,9,n_matrix))
      }

      "rand erkennung oben" in {
        val n_matrix = new Matrix(9)
        zoomer.show(UIEvent.MoveUp) should be(Zoom(8,7,n_matrix))
      }

      "rand erkennung links" in {
        val n_matrix = new Matrix(9)
        zoomer.show(UIEvent.MoveLeft) should be(Zoom(7,8,n_matrix))
      }

      "rand erkennung rechts" in {
        val n_matrix = new Matrix(9)
        zoomer.show(UIEvent.MoveRight) should be(Zoom(9,8,n_matrix))
      }

      "rand erkennung any" in {
        val n_matrix = new Matrix(9)
        zoomer.show(UIEvent.StartCombat) should be(Zoom(8,8,n_matrix))
      }

    }
  }

}
