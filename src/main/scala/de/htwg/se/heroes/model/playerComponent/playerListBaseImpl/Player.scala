package de.htwg.se.heroes.model.playerComponent.playerListBaseImpl

import de.htwg.se.heroes.controllerComponent.controllerBaseImpl.gamemode.UIEvent.{BuyMelee, BuyRange}
import de.htwg.se.heroes.model.playerComponent.PlayerInterface
import de.htwg.se.heroes.model.soldier.SoldierInterface
import de.htwg.se.heroes.model.soldier.soldierBaseImpl.{MeleeSoldier, RangeSoldier, Soldier}
//import de.htwg.se.heroes.model.soldier.soldierBaseImpl.{MeleeSoldier, RangeSoldier, Soldier}

import scala.collection.immutable.ListMap

case class Player(name: String, gold: Int, strength: Int, units: ListMap[Soldier, Int], x: Int, y: Int) extends PlayerInterface {

  override def toString: String = name + ": Gold: " + gold + " Strength: " + strength + " Units: " + units.toString()

  def walk(nx: Int, ny: Int): Player = copy(name, gold, strength, units, x + nx, y + ny)

  def addUnit(unit: Soldier, amount: Int, costs: Int): Player = {
    if(units contains unit) {
      println("map contains " + unit)
      val tmp = units(unit) + amount
      copy(name, gold - costs, strength, units + (unit -> tmp), x, y)
    } else {
      copy(name, gold - costs, strength, units + (unit -> amount), x, y)
    }
  }

  def moveUnit(xs: Int, ys: Int, remove: Soldier): Player = {
    val amount = units.getOrElse(remove, 0)
    var f =  removeUnit(remove)
    val typ = remove match {
      case f:MeleeSoldier => new MeleeSoldier(xs,ys)
      case r:RangeSoldier => new RangeSoldier(xs,ys)
      case _ => new MeleeSoldier(1,1) //TODO Flaschentransport
    }
    f = f.copy(name, gold, strength, f.units + (typ -> amount), x, y)
    f
  }

  def removeUnit(remove: Soldier): Player = {
    copy(name, gold, strength, units - remove, x, y)
  }

  def powerUp(str: Int): Player = copy(name, gold, str + strength, units, x, y)

  def setGold(n_gold: Int): Player = copy(name, n_gold + gold, strength, units, x, y)
}
