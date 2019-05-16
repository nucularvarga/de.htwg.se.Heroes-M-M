package de.htwg.se.heroes.model

import scala.collection.immutable.ListMap


class PlayerList {

  var playerBase = Vector.empty[Player]
  var PlayerTurn = 0
  var attackUnit = 0
  var defendUnit = 0

  def addPlayer(n: String, gold: Int, str: Int, units: Map[Soldier, Int], row: Int, col: Int): PlayerList = {
    playerBase = playerBase :+ Player(n, gold, str, units, row, col)
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

  def getAttackUnit: Soldier = {
    if(attackUnit >= playerBase(PlayerTurn).units.size) {
      attackUnit = 0
    }
    playerBase(PlayerTurn).units.toList(attackUnit)._1
  }

  def nextDefendUnit: Unit = {
    defendUnit += 1
  }

  def updateAttackUnit: PlayerList = {
    this
  }

  def getDefendUnit: Soldier = {
    if(defendUnit >= playerBase(PlayerTurn).units.size) {
      defendUnit = 0
    }
    playerBase(PlayerTurn).units.toList(defendUnit)._1
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
  def updatePlayer(str: Int, row: Int, col: Int): PlayerList = {
    if(PlayerTurn >= playerBase.length) {
      PlayerTurn = 0
    }
    playerBase = playerBase.updated(PlayerTurn, playerBase(PlayerTurn).walk(row, col))
    playerBase = playerBase.updated(PlayerTurn, playerBase(PlayerTurn).powerUp(str))
    this
  }

  def setUnits(number: Int, cost: Int): PlayerList = {
    playerBase = playerBase.updated(PlayerTurn, playerBase(PlayerTurn).addUnit(Soldier(1,1), number, cost))
    this
  }

}
