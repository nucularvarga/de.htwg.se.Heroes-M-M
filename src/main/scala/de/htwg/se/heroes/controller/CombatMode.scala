package de.htwg.se.heroes.controller

import de.htwg.se.heroes.controller.Event.Event
import de.htwg.se.heroes.model._

case class CombatMode(var playArena: Arena, playerBase: PlayerList, enemy: EnemyCell) extends  GameMode {

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

  def initArena(): GameMode = {
    playArena = playArena.initArena
    setSoldier(enemy)
  }

  def action(d: Event) : GameMode = {
    val cell = playArena.cell(playerBase.getPlayer.x + calcDir(d)._1, playerBase.getPlayer.y + calcDir(d)._2)
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
    CombatMode(playArena, playerBase, enemy)
  }


  def setSoldier(enemy: EnemyCell): GameMode = {
    var unitVector: Vector[Cell] = Vector.empty
    for (e <- playerBase.getPlayer.units) unitVector = unitVector :+ e._1
    for {row <- 0 until playerBase.getPlayer.units.size} {
      playArena = playArena.set(row + 1, 2, unitVector(row))
      playArena = playArena.set(row + 1, 27, enemy)
    }
    CombatMode(playArena, playerBase, enemy)
  }
    override def toString: String = playArena.toString
}
