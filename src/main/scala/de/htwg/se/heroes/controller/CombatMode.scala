package de.htwg.se.heroes.controller

import de.htwg.se.heroes.controller.Event.Event
import de.htwg.se.heroes.model._

case class CombatMode(var playArena: Arena, playerBase: PlayerList, enemy: EnemyCell) extends  GameMode {
  println("Spieler: " + playerBase.getPlayer)
  val itOrder = playerBase.getPlayer.units.createIterator
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
    itOrder.hasNext
    val cell = playArena.cell(itOrder.ptr.x + x, itOrder.ptr.y + y)
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
    val soldier = itOrder.ptr
    playArena = playArena.set(itOrder.ptr.x,  itOrder.ptr.y, Leer())
    playArena = playArena.set(soldier.x + x, soldier.y + y, soldier)
    val units = playerBase.getPlayer.units.move(Soldier(soldier.x, soldier.y), 5, x, y)
    println(units.units.head._1.x + "|" + units.units.head._1.y)
    playerBase.playerBase = playerBase.playerBase.updated(playerBase.PlayerTurn, playerBase.getPlayer.unit(units))
    //playerBase.nextPlayer
    CombatMode(playArena, playerBase, enemy)
  }


  def setSoldier(enemy: EnemyCell): GameMode = {
    var unitVector: Vector[Soldier] = Vector.empty
    val itOrder = playerBase.getPlayer.units.createIterator
    while(itOrder.hasNext) {
      unitVector = unitVector :+ itOrder.next
    }
    for {list <- unitVector.indices} {
      playArena = playArena.set(unitVector(list).x, list + unitVector(list).y, unitVector(list))
      playArena = playArena.set(27, 1 + list, enemy)
    }
    CombatMode(playArena, playerBase, enemy)
  }
    override def toString: String = playArena.toString
}
