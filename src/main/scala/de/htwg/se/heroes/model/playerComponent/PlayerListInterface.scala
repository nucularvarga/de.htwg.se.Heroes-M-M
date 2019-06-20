package de.htwg.se.heroes.model.playerComponent

trait PlayerListInterface {
  def addPlayer(n: String, gold: Int, str: Int, units: Map[Soldier, Int], x: Int, y: Int): PlayerList
  def moveunit(x: Int, y: Int, sol: Soldier): PlayerList
  def nextPlayer: PlayerList
  def getPlayer: Player
  def nextAttackUnit: Unit
  def getAttackUnit: Soldier
  def nextDefendUnit: Unit
  def updateAttackUnit: PlayerList
  def getDefendUnit: Soldier
  def updatePlayer(str: Int, x: Int, y: Int): PlayerList
  def setUnits(number: Int, cost: Int): PlayerList
}

trait PlayerInterface {
  def walk(nx: Int, ny: Int): Player
  def addUnit(unit: Soldier, amount: Int, costs: Int): Player
  def moveUnit(xs: Int, ys: Int, remove: Soldier): Player
  def removeUnit(remove: Soldier): Player
  def powerUp(str: Int): Player
}