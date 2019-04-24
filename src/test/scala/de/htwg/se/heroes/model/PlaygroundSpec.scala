package de.htwg.se.heroes.model

import org.scalatest.{Matchers, WordSpec}

class PlaygroundSpec extends WordSpec with Matchers {

  "A Playground" when {
    "set emptyfield with one player" should {
      var emptyground = new Playground(5)
      emptyground.playerbase =  emptyground.setPlayer("Ich", 0, 100, 2,1)
      "when " in {
        emptyground.playerbase(0) should be(Player("Ich", 0, 100, 2,1))
      }
    }

    "5x5 Field with basic tokens" should {
      var emptyground = new Playground(5)
      var emptyloseground = new Playground(5)
      var evalground = new Playground(5)
      var evalloseground = new Playground(5)

      emptyground.playfield = emptyground.init
      emptyground.playfield = evalground.init
      evalloseground.playfield = evalloseground.init
      emptyloseground.playfield = emptyloseground.init

      emptyground.playfield = emptyground.replaceField(2, 1, HeroCell("1"))
      emptyground.playfield = emptyground.replaceField(2,2, EnemyCell(5))
      emptyground.playerbase = emptyground.setPlayer("Du", 0, 100, 2, 1)

      emptyloseground.playfield = emptyground.replaceField(2, 1, HeroCell("1"))
      emptyloseground.playfield = emptyground.replaceField(2,2, EnemyCell(5))
      emptyloseground.playerbase = emptyground.setPlayer("Du", 0, 100, 2, 1)

      evalloseground.playfield = emptyground.replaceField(2, 1, HeroCell("1"))
      evalloseground.playfield = emptyground.replaceField(2,2, EnemyCell(5))

      evalground.playfield = evalground.replaceField(2,2, HeroCell("1"))

      "after battle player move" in {
        emptyground.attack(2, 2, 5, 0) should be(evalground.playfield)
        emptyloseground.attack(2, 2, 115, 0) should be(evalloseground.playfield)
      }
    }

    "5x5 field with one player movement" should {
      var emptyground = new Playground(5)
      emptyground.playfield = emptyground.init
      emptyground.playerbase = emptyground.setPlayer("Ich", 0, 100, 2, 2)
      "after player pressed direction " in {
        emptyground.goodmove("w", 0) should be(true)
        emptyground.goodmove("a", 0) should be(true)
        emptyground.goodmove("s", 0) should be(true)
        emptyground.goodmove("d", 0) should be(true)

        emptyground.goodmove("t", 0) should be(false)
        emptyground.goodmove("h", 0) should be(false)
      }
    }
  }
}
