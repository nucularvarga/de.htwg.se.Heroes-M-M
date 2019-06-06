package de.htwg.se.heroes.controller

import de.htwg.se.heroes.controller.Event.Event
import de.htwg.se.heroes.model._

case class CombatMode(playArena: Arena, playerBase: PlayerList, enemy: EnemyCell) extends  GameMode {
  println("CombatMode activ")


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

  def updateArena(arena: Arena): CombatMode = copy(arena, playerBase, enemy)

  def initArena(): GameMode = {
    val f = updateArena(playArena.initArena)
    f.setSoldier(f.enemy)
  }

  def action(d: Event) : GameMode = {
    val (x, y) = calcDir(d)
    val cell = playArena.cell(playerBase.getAttackUnit.x + x,  playerBase.getAttackUnit.y + y)   //playArena.cell(playerBase.getPlayer.x + calcDir(d)._1, playerBase.getPlayer.y + calcDir(d)._2)
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

    println(playerBase.getAttackUnit.x +"|"+  playerBase.getAttackUnit.y)
    var f =  updateArena(playArena.set(playerBase.getAttackUnit.x,  playerBase.getAttackUnit.y, Leer()))
    f = f.updateArena(f.playArena.set(playerBase.getAttackUnit.x + x, f.playerBase.getAttackUnit.y + y, f.playerBase.getAttackUnit))
    //f = f.updatePlayerBase(playerBase.playerBase.updated(0, playerBase.getPlayer.moveUnit(playerBase.getAttackUnit.x + x, playerBase.getAttackUnit.y + y, playerBase.getAttackUnit)))
    playerBase.nextAttackUnit
    f
  }

  override def updatePlayerBase(base: PlayerList): GameMode = copy(playArena, base)

  def setSoldier(enemy: EnemyCell): GameMode = {
    var unitVector: Vector[Soldier] = Vector.empty
    var f = this
    for (e <- playerBase.getPlayer.units) unitVector = unitVector :+ e._1
    for {list <- 0 until playerBase.getPlayer.units.size} {
      f = f.updateArena(f.playArena.set(unitVector(list).x, list + unitVector(list).y, unitVector(list)))
      f = f.updateArena(f.playArena.set(7, 1 + list, enemy))
    }
    //CombatMode(playArena, playerBase, enemy)
    f
  }
  override def toString: String = playArena.toString

  override def cell(x: Int, y: Int): Cell = playArena.cell(x,y)

  override def  playlist: PlayerList = playerBase


}
