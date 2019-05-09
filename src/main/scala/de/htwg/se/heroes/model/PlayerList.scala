package de.htwg.se.heroes.model



class PlayerList {

  var playerBase = Vector.empty[Player]
  var PlayerTurn = 0

  def addPlayer(n: String, gold: Int, str: Int, unit: Map[Cell, Int], row: Int, col: Int): PlayerList = {
    playerBase = playerBase :+ Player(n, gold, str, unit, row, col)
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

/*
  def updatePlayer(player: Player, row: Int, col: Int): PlayerList = {
    if(PlayerTurn >= playerBase.length) {
      PlayerTurn = 0
    }
    playerBase = playerBase.updated(PlayerTurn, playerBase(PlayerTurn).walk(row, col))
    this
  }
*/
  def updatePlayer(player: Player, str: Int, row: Int, col: Int): PlayerList = {
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
