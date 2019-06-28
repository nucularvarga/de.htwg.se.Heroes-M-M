package de.htwg.se.heroes.model.playerComponent.playerListBaseImpl

import de.htwg.se.heroes.model.playerComponent.PlayerInterface
import de.htwg.se.heroes.model.soldier.SoldierInterface
import de.htwg.se.heroes.model.soldier.soldierBaseImpl.Soldier

case class Player(name: String, gold: Int, strength: Int, units: Map[SoldierInterface, Int], x: Int, y: Int) extends PlayerInterface {

  override def toString: String = name + ": Gold: " + gold + " Strength: " + strength + " Units: " + units.toString()

  def walk(nx: Int, ny: Int): Player = copy(name, gold, strength, units, x + nx, y + ny)

  def addUnit(unit: SoldierInterface, amount: Int, costs: Int): Player = {
    if(units contains unit) {
      println("map contains " + unit)
      val tmp = units(unit) + amount
      copy(name, gold - costs, strength, units + (unit -> tmp), x, y)
    } else {
      copy(name, gold - costs, strength, units + (unit -> amount), x, y)
    }
  }

  def moveUnit(xs: Int, ys: Int, remove: SoldierInterface): Player = {
    var f =  removeUnit(remove)
    println(f)
    f = f.copy(name, gold, strength, f.units + (remove -> 5), x, y)
    f
  }

  def removeUnit(remove: SoldierInterface): Player = {
    copy(name, gold, strength, units - remove, x, y)
  }

  def powerUp(str: Int): Player = copy(name, gold, str + strength, units, x, y)
}
