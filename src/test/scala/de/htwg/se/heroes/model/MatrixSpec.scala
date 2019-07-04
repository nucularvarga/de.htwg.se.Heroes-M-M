package de.htwg.se.heroes.model

import de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl.{Cell, Field, HeroCell, Matrix}
import org.scalatest.{Matchers, WordSpec}

class MatrixSpec extends WordSpec with Matchers {

  "A Matrix" when {
    "set to a specific value" should {
      val EmptyMatrix = new Matrix(5)
      val size = 5
      "print field" in {
        EmptyMatrix.toString should be(((" " * size) + "\n") * size)
      }
    }
  }

}