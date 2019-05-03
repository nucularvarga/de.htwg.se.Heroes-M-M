package de.htwg.se.heroes.model


import org.scalatest.{Matchers, WordSpec}

class ArenaSpec extends WordSpec with Matchers {

  "A Arena" when {
    "set to a specific value" should {
      val EmptyArena = new Arena(5, 10)
      val height = 5
      val length = 10
      val printField = new Arena(height, length)
      val cell = new Cell
      val hero = HeroCell("1")
      val newArena = new Arena(5, 10)
      "return that value" in {
        EmptyArena.cells.length should be(10)
        EmptyArena.cell(1,1).typ should be(cell.typ)
        newArena.set(1, 1, hero).cell(1,1) should be(hero)
      }
      "print field" in {
        printField.toString should be(((" " * length) + "\n") * height)
      }
    }
  }

}