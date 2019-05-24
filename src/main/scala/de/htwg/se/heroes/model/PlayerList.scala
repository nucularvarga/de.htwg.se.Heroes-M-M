package de.htwg.se.heroes.model

import scala.collection.immutable.ListMap


case class PlayerList(playerBase: Vector[Player], var PlayerTurn: Int) {

  var attackUnit = 0
  var defendUnit = 0

  def addPlayer(n: String, gold: Int, str: Int, units: Map[Soldier, Int], x: Int, y: Int): PlayerList = {
    copy(playerBase :+ Player(n, gold, str, units, x, y))
  }

  def nextPlayer: PlayerList = {
    copy(playerBase, PlayerTurn + 1)
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
  def updatePlayer(str: Int, x: Int, y: Int): PlayerList = {
    if(PlayerTurn >= playerBase.length) {
      PlayerTurn = 0
    }
    var f = this
    f = f.copy(playerBase.updated(PlayerTurn, playerBase(PlayerTurn).walk(x, y)))
    f.copy(f.playerBase.updated(PlayerTurn, f.playerBase(PlayerTurn).powerUp(str)))
  }

  def setUnits(number: Int, cost: Int): PlayerList = {
    copy(playerBase.updated(PlayerTurn, playerBase(PlayerTurn).addUnit(Soldier(1,1), number, cost)))
  }

}
