package de.htwg.se.heroes.model

import scala.collection.immutable.ListMap


class PlayerList {

  var playerBase = Vector.empty[Player]
  var PlayerTurn = 0
  var attackUnit = 0
  var defendUnit = 0

  def addPlayer(n: String, gold: Int, str: Int, units: UnitOrder, x: Int, y: Int): PlayerList = {
    playerBase = playerBase :+ Player(n, gold, str, units, x, y)
    this
  }

  def nextPlayer: Unit = {
    PlayerTurn += 1
  }

  def getPlayer: Player = {
    if(PlayerTurn >= playerBase.length) {
      PlayerTurn = 0
    }
    playerBase(PlayerTurn)
  }

  def nextAttackUnit: Unit = {
    attackUnit += 1
  }
/*
  def getAttackUnit: Soldier = {
    if(attackUnit >= playerBase(PlayerTurn).units.size) {
      attackUnit = 0
    }
    playerBase(PlayerTurn).units.toList(attackUnit)._1
  }
*/
  def nextDefendUnit: Unit = {
    defendUnit += 1
  }

  def updateAttackUnit: PlayerList = {
    this
  }

/*
  def updatePlayer(player: Player, row: Int, col: Int): PlayerList = {
    if(PlayerTurn >= playerBase.length) {
      PlayerTurn = 0
    }
    playerBase = playerBase.updated(PlayerTurn, playerBase(PlayerTurn).walk(row, col))
    this
  }
*/
  def updatePlayer(str: Int, x: Int, y: Int): PlayerList = {
    if(PlayerTurn >= playerBase.length) {
      PlayerTurn = 0
    }
    playerBase = playerBase.updated(PlayerTurn, playerBase(PlayerTurn).walk(x, y))
    playerBase = playerBase.updated(PlayerTurn, playerBase(PlayerTurn).powerUp(str))
    this
  }

  def setUnits(number: Int, cost: Int): PlayerList = {
    val list = getPlayer.units.addUnit(Soldier(1,1), number)
    playerBase = playerBase.updated(PlayerTurn, getPlayer.buy(cost))
    playerBase = playerBase.updated(PlayerTurn,  playerBase(PlayerTurn).unit(list))
    this
  }

}
