package de.htwg.se.heroes.model.playerComponent.playerListBaseImpl

import de.htwg.se.heroes.model.playerComponent.PlayerListInterface
import de.htwg.se.heroes.model.soldier.SoldierInterface
import de.htwg.se.heroes.model.soldier.soldierBaseImpl.Soldier

import scala.collection.immutable.ListMap

case class PlayerList(playerBase: Vector[Player], var PlayerTurn: Int) extends PlayerListInterface {

  var attackUnit = 0
  var defendUnit = 0


  override def getSize: Int = playerBase.size

  override def getTurn: Int = PlayerTurn

  def addPlayer(n: String, gold: Int, str: Int, units: ListMap[Soldier, Int], x: Int, y: Int): PlayerList = {
    copy(playerBase :+ Player(n, gold, str, units, x, y))
  }

  def moveunit(x: Int, y: Int, sol: Soldier): PlayerList = {
    copy(playerBase.updated(PlayerTurn, getPlayer.moveUnit(x, y, sol)))
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

  def getPlayer(number: Int): Player = {
    if(number >= playerBase.length) {
      playerBase(0)
    } else
    playerBase(number)

  }

  def nextAttackUnit: PlayerListInterface = {
    attackUnit += 0
    this
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

  def getDefendUnit: SoldierInterface = {
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

  def updatePlayerGold(gold: Int): PlayerListInterface = {
    if(PlayerTurn >= playerBase.length) {
      PlayerTurn = 0
    }
    val f = this
    f.copy(f.playerBase.updated(PlayerTurn, f.playerBase(PlayerTurn).setGold(gold)))
  }


  def setUnits(typ: Soldier, number: Int, cost: Int): PlayerList = {
    copy(playerBase.updated(PlayerTurn, playerBase(PlayerTurn).addUnit(typ, number, cost)))
  }

}
