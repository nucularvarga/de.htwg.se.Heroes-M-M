package de.htwg.se.heroes.controller

import de.htwg.se.heroes.controller.Event.{Event, MoveDown, MoveLeft, MoveRight, MoveUp, StartCombat}
import de.htwg.se.heroes.model._

case class MapMode(playField: Field, playerBase: PlayerList) extends GameMode {
  var enemy: EnemyCell = EnemyCell(0)
  override def handle(e: Event):GameMode = {
    e match {
      case Event.StartCombat => CombatMode(  new Arena(30,10), playerBase, enemy).handle(StartCombat)
      case Event.EndCombat => MapMode(playField, playerBase)
      case Event.MoveUp => action(Event.MoveUp)
      case Event.MoveLeft => action(Event.MoveLeft)
      case Event.MoveRight => action(Event.MoveRight)
      case Event.MoveDown => action(Event.MoveDown)
    }
  }

  def updateField(play: Field): MapMode = copy(play, playerBase)

  def updatePlayerBase(base: PlayerList): MapMode = copy(playField, base)

  def move(e: Event): GameMode = {
    val (x, y) = calcDir(e)
    var f = this
    f = f.updateField(f.playField.set(f.playerBase.getPlayer.x, f.playerBase.getPlayer.y, Leer()))
    f = f.updateField(f.playField.set(f.playerBase.getPlayer.x + x, f.playerBase.getPlayer.y + y, HeroCell(f.playerBase.getPlayer.name)))
    f = f.updatePlayerBase(f.playerBase.updatePlayer(0, x, y))
    println(f.playerBase.getPlayer.x + " " + f.playerBase.getPlayer.y)
    f.updatePlayerBase(f.playerBase.nextPlayer) // TODO next? iterator?
  }

  def action(d : Event): GameMode = {
    val (x, y) = calcDir(d)
    val cell = playField.cell(playerBase.getPlayer.x + x, playerBase.getPlayer.y + y)
    println(cell)
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

