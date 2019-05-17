package de.htwg.se.heroes.controller

import de.htwg.se.heroes.controller.Event.Event
import de.htwg.se.heroes.model._

case class CombatMode(var playArena: Arena, playerBase: PlayerList, enemy: EnemyCell) extends  GameMode {
  println("Spieler: " + playerBase.getPlayer)
  override def handle(e: Event):GameMode = {
    e match {
      //case Event.StartCombat => CombatMode()
      case Event.StartCombat => initArena()
      case Event.MoveUp => action(Event.MoveUp)
      case Event.MoveLeft => action(Event.MoveLeft)
      case Event.MoveRight => action(Event.MoveRight)
      case Event.MoveDown => action(Event.MoveDown)
    }
  }

  def initArena(): GameMode = {
    playArena = playArena.initArena
    setSoldier(enemy)
  }

  def action(d: Event) : GameMode = {
    val (x, y) = calcDir(d)
    val cell = playArena.cell(playerBase.getAttackUnit.x + x,  playerBase.getAttackUnit.y + y)
    cell match {
      case Leer() => move(d)
      case Stop() => CombatMode(playArena, playerBase, enemy)
      case f: Soldier => fight(f)
    }
  }

  def fight(soldir: Soldier): GameMode = {
    CombatMode(playArena, playerBase, enemy)
  }

  def move(e: Event): GameMode = {
    val (x, y) = calcDir(e)
    //ITERATOR WICHTIG SUPER WICHTIG
    val itOrder = playerBase.getPlayer.units.createIterator
    playArena = playArena.set(playerBase.getAttackUnit.x,  playerBase.getAttackUnit.y, Leer())
    playArena = playArena.set(playerBase.getAttackUnit.x + x, playerBase.getAttackUnit.y + y, playerBase.getAttackUnit)
    playerBase.playerBase = playerBase.playerBase.updated(playerBase.PlayerTurn, playerBase.getPlayer.moveUnit(playerBase.getAttackUnit.x + x, playerBase.getAttackUnit.y + y, playerBase.getAttackUnit))
    playerBase.nextAttackUnit
    playerBase.nextPlayer
    CombatMode(playArena, playerBase, enemy)
  }


  def setSoldier(enemy: EnemyCell): GameMode = {
    var unitVector: Vector[Soldier] = Vector.empty
    for (e <- playerBase.getPlayer.units) unitVector = unitVector :+ e._1
    for {list <- 0 until playerBase.getPlayer.units.size} {
      playArena = playArena.set(unitVector(list).x, list + unitVector(list).y, unitVector(list))
      playArena = playArena.set(27, 1 + list, enemy)
    }
    CombatMode(playArena, playerBase, enemy)
  }
    override def toString: String = playArena.toString
}
