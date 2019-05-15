package de.htwg.se.heroes.controller

import de.htwg.se.heroes.controller.Event.{Event, MoveDown, MoveLeft, MoveRight, MoveUp, StartCombat}
import de.htwg.se.heroes.model._

case class MapMode(var playField: Field, var playerBase: PlayerList) extends GameMode {
  println("MapMode activ")
  var enemy: EnemyCell = EnemyCell(0)
  override def handle(e: Event):GameMode = {
    e match {
      case Event.StartCombat => CombatMode(  new Arena(10,30), playerBase, enemy).handle(StartCombat)
      case Event.EndCombat => MapMode(playField, playerBase)
      case Event.MoveUp => action(Event.MoveUp)
      case Event.MoveLeft => action(Event.MoveLeft)
      case Event.MoveRight => action(Event.MoveRight)
      case Event.MoveDown => action(Event.MoveDown)
    }
  }

  def move(e: Event): GameMode = {
    val (row, col) = calcDir(e)
    playField = playField.set(playerBase.getPlayer.x, playerBase.getPlayer.y, Leer())
    playField = playField.set(playerBase.getPlayer.x + row, playerBase.getPlayer.y + col, HeroCell(playerBase.getPlayer.name))
    playerBase = playerBase.updatePlayer(0, calcDir(e)._1, calcDir(e)._2)
    playerBase.nextPlayer // TODO next? iterator?
    MapMode(playField, playerBase)
  }

  def action(d : Event): GameMode = {
    val cell = playField.cell(playerBase.getPlayer.x + calcDir(d)._1, playerBase.getPlayer.y + calcDir(d)._2)
    cell match {
      case Leer() => move(d)
      case Stop() => MapMode(playField, playerBase)
      case f: EnemyCell => startBattle(f)
      case _ => MapMode(playField, playerBase)
    }
  }

  def startBattle(enemys: EnemyCell): GameMode = {
    enemy = enemys
    println("start battle")
    handle(StartCombat)//CombatMode(playerBase, enemy)
  }

  override def toString: String = playField.toString
}

