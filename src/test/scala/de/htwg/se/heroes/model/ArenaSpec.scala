package de.htwg.se.heroes.model


import de.htwg.se.heroes.model.fieldComponent.{Arena, Cell, HeroCell}
import org.scalatest.{Matchers, WordSpec}

class ArenaSpec extends WordSpec with Matchers {

  "A Arena" when {
    "set to a specific value" should {
      val EmptyArena = new Arena(10, 5)
      val height = 5
      val length = 10
      val printField = new Arena(length, height)
      val cell = new Cell
      val hero = HeroCell("1")
      val newArena = new Arena(10, 5)
      "return that value" in {
        EmptyArena.cells.length should be(10)
        EmptyArena.cell(1,1).typ should be(cell.typ)
        newArena.set(1, 1, hero).cell(1,1) should be(hero)
      }
      "print field" in {
        printField.toString should be(((" " * length) + "\n") * height)
      }
      "init arena" in {
        var arena = new Arena(2, 2)
        arena = arena.initArena
        arena.toString should be("XX\nXX\n")
      }
    }
  }

}