package de.htwg.se.heroes.controller

import de.htwg.se.heroes.controller.UIEvent.UIEvent
import de.htwg.se.heroes.model._

case class CombatMode(playArena: Arena, playerBase: PlayerList, enemy: EnemyCell, map: MapMode) extends  GameMode {

  var unitVector: Vector[Soldier] = Vector.empty
  override def handle(e: UIEvent):GameMode = {
    e match {
      case UIEvent.WinEndCombat => map.handle(UIEvent.WinEndCombat)
      case UIEvent.LoseEndCombat => map.handle(UIEvent.LoseEndCombat)
      case UIEvent.StartCombat => initArena()
      case UIEvent.MoveUp => action(UIEvent.MoveUp)
      case UIEvent.MoveLeft => action(UIEvent.MoveLeft)
      case UIEvent.MoveRight => action(UIEvent.MoveRight)
      case UIEvent.MoveDown => action(UIEvent.MoveDown)
    }
  }

  def updateArena(arena: Arena): CombatMode = copy(arena, playerBase, enemy)

  def initArena(): CombatMode = {
    println("action init")
    val f = updateArena(playArena.initArena)
    f.setSoldier(f.enemy)
  }

  def action(d: UIEvent) : GameMode = {
    val (x, y) = calcDir(d)
    val cell = playArena.cell(playerBase.getAttackUnit.x + x,  playerBase.getAttackUnit.y + y)   //playArena.cell(playerBase.getPlayer.x + calcDir(d)._1, playerBase.getPlayer.y + calcDir(d)._2)
    cell match {
      case Leer() => move(d)
      case Stop() => CombatMode(playArena, playerBase, enemy, map)
      case f: EnemyCell => fight(f,d)
    }
  }

  def fight(soldir: EnemyCell, d:UIEvent): GameMode = {
    if( soldir.strength > playerBase.getAttackUnit.str)
      handle(UIEvent.LoseEndCombat)
    else
      handle(UIEvent.WinEndCombat)
  }

  def move(e: UIEvent): CombatMode = {
    val (x, y) = calcDir(e)
    var f = this
    f =  updateArena(playArena.set(playerBase.getAttackUnit.x,  playerBase.getAttackUnit.y, Leer()))
    f = f.updateArena(f.playArena.set(f.playerBase.getAttackUnit.x + x, f.playerBase.getAttackUnit.y + y, f.playerBase.getAttackUnit))
    f = f.updatePlayerBase(f.playerBase.moveunit(f.playerBase.getAttackUnit.x + x, f.playerBase.getAttackUnit.y + y, f.playerBase.getAttackUnit))
    //f.playerBase.nextAttackUnit
    f
  }

  override def updatePlayerBase(base: PlayerList): CombatMode = copy(playArena, base)

  def setSoldier(enemy: EnemyCell): CombatMode = {
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
