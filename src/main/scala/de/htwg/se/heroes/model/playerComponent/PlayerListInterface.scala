package de.htwg.se.heroes.model.playerComponent

import de.htwg.se.heroes.model.playerComponent.playerListBaseImpl.Player
import de.htwg.se.heroes.model.soldier.SoldierInterface
import de.htwg.se.heroes.model.soldier.soldierBaseImpl.Soldier

import scala.collection.immutable.ListMap

trait PlayerListInterface {
  def addPlayer(n: String, gold: Int, str: Int, units: Map[SoldierInterface, Int], x: Int, y: Int): PlayerListInterface
  def moveunit(x: Int, y: Int, sol: SoldierInterface): PlayerListInterface
  def nextPlayer: PlayerListInterface
  def getPlayer: Player
  def getPlayer(number: Int): Player
  def nextAttackUnit: Unit
  def getAttackUnit: SoldierInterface
  def nextDefendUnit: Unit
  def updateAttackUnit: PlayerListInterface
  def getDefendUnit: SoldierInterface
  def getTurn: Int
  def getSize: Int
  def updatePlayer(str: Int, x: Int, y: Int): PlayerListInterface
  def updatePlayerGold(gold: Int): PlayerListInterface
  def setUnits(typ: SoldierInterface, number: Int, cost: Int): PlayerListInterface
}

trait PlayerInterface {
  def walk(nx: Int, ny: Int): Player
  def addUnit(unit: SoldierInterface, amount: Int, costs: Int): Player
  def moveUnit(xs: Int, ys: Int, remove: SoldierInterface): Player
  def removeUnit(remove: SoldierInterface): Player
  def powerUp(str: Int): Player
  def setGold(gold: Int): Player
}