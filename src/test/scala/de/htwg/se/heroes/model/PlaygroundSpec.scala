package de.htwg.se.heroes.model

import org.scalatest.{Matchers, WordSpec}
import de.htwg.se.heroes.controller.Direction
import de.htwg.se.heroes.controller.Direction._

class PlaygroundSpec extends WordSpec with Matchers {

  "A Playground" when {
    "5x5 Field with basic tokens" should {
      val emptyground = new Playground(5)
      "after battle player move" in {
        emptyground.calcDirection(Direction.Up) should be((-1,0))
        emptyground.calcDirection(Direction.Down) should be((1,0))
        emptyground.calcDirection(Direction.Left) should be((0,-1))
        emptyground.calcDirection(Direction.Right) should be((0,1))
      }
    }

  }
}
