package de.htwg.se.heroes.model


import de.htwg.se.heroes.model.soldier.soldierBaseImpl.{MeleeSoldier, RangeSoldier, Soldier}
import org.scalatest.{Matchers, WordSpec}

class SoldierSpec extends WordSpec with Matchers {

  "A Cell" when {
    "a new ranger " should {
      val ranger = new RangeSoldier(0,0)
      "have value ' '" in {
        ranger.typ should be("R")
        ranger.toString should be("R")
        ranger.cost should be(40)
        ranger.str should be(10)
        ranger.getRange should be(1)
      }
    }
    "a new melee" should {
      val melee = new MeleeSoldier(0,0)
      "have value" in {
        melee.getRange should be(1)
      }
    }
    "a new soldier" should {
      val sol = new Soldier(0,0)
      "have value" in {
        sol.getRange should be(1)
        sol.getTyp should be("S")
        sol.getCost should be(10)
        sol.getStrength should be(5)
      }
    }
  }

}