package de.htwg.se.heroes.model

import org.scalatest.{Matchers, WordSpec}

class FieldSpec extends WordSpec with Matchers {

  "A Field" when {
    "set to a specific value" should {
      val EmptyField = new Field(5)
      val size = 2
      val printField = new Field(size)
      val cell = new Cell
      val hero = HeroCell("1")
      val newField = new Field(5)
      "return that value" in {
        EmptyField.rows.size should be(5)
        EmptyField.rows(0).size should be(5)
        EmptyField.cell(1,1).typ should be(cell.typ)
        newField.replaceCell(1, 1, hero).cell(1,1) should be(hero)
      }
      "print field" in {
        printField.toString should be(((" " * size) + "\n") * size)
      }
    }
  }

}