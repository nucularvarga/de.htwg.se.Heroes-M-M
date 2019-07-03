package de.htwg.se.heroes.model.playerComponent

import de.htwg.se.heroes.model.playerComponent.playerListBaseImpl.Player
import de.htwg.se.heroes.model.soldier.SoldierInterface
import de.htwg.se.heroes.model.soldier.soldierBaseImpl.Soldier

import scala.collection.immutable.ListMap

trait PlayerListInterface {
  def addPlayer(n: String, gold: Int, str: Int, units: ListMap[Soldier, Int], x: Int, y: Int): PlayerListInterface
  def moveunit(x: Int, y: Int, sol: Soldier): PlayerListInterface
  def nextPlayer: PlayerListInterface
  def getPlayer: Player
  def getPlayer(number: Int): Player
  def nextAttackUnit: PlayerListInterface
  def getAttackUnit: Soldier
  def nextDefendUnit: Unit
  def updateAttackUnit: PlayerListInterface
  def getDefendUnit: SoldierInterface
  def getTurn: Int
  def getSize: Int
  def updatePlayer(str: Int, x: Int, y: Int): PlayerListInterface
  def updatePlayerGold(gold: Int): PlayerListInterface
  def setUnits(typ: Soldier, number: Int, cost: Int): PlayerListInterface
}

trait PlayerInterface {
  def walk(nx: Int, ny: Int): Player
  def addUnit(unit: Soldier, amount: Int, costs: Int): Player
  def moveUnit(xs: Int, ys: Int, remove: Soldier): Player
  def removeUnit(remove: Soldier): Player
  def powerUp(str: Int): Player
  def setGold(gold: Int): Player
}