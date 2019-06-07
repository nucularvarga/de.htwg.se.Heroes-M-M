package de.htwg.se.heroes.controller

import de.htwg.se.heroes.controller.UIEvent.{UIEvent, MoveDown, MoveLeft, MoveRight, MoveUp, StartCombat}
import de.htwg.se.heroes.model._

case class MapMode(playField: Field, playerBase: PlayerList) extends GameMode {
  var enemy: EnemyCell = EnemyCell(0)
  private var t: GameMode = this
  override def handle(e: UIEvent):GameMode = {
    e match {
      case UIEvent.StartCombat => CombatMode(new Arena(9,9), playerBase, enemy, this).handle(StartCombat)
      case UIEvent.WinEndCombat => winMove
      case UIEvent.LoseEndCombat => MapMode(playField, playerBase)
      case UIEvent.MoveUp => action(UIEvent.MoveUp)
      case UIEvent.MoveLeft => action(UIEvent.MoveLeft)
      case UIEvent.MoveRight => action(UIEvent.MoveRight)
      case UIEvent.MoveDown => action(UIEvent.MoveDown)
    }
  }

  def winMove: GameMode = {
    t
  }

  def updateField(play: Field): MapMode = copy(play, playerBase)

  override def updatePlayerBase(base: PlayerList): MapMode = copy(playField, base)

  def move(e: UIEvent): GameMode = {
    val (x, y) = calcDir(e)
    var f = this
    f = f.updateField(f.playField.set(f.playerBase.getPlayer.x, f.playerBase.getPlayer.y, Leer()))
    f = f.updateField(f.playField.set(f.playerBase.getPlayer.x + x, f.playerBase.getPlayer.y + y, HeroCell(f.playerBase.getPlayer.name)))
    f = f.updatePlayerBase(f.playerBase.updatePlayer(0, x, y))
    println(f.playerBase.getPlayer.x + " " + f.playerBase.getPlayer.y)
    f.updatePlayerBase(f.playerBase.nextPlayer) // TODO next? iterator?
  }

  def action(d : UIEvent): GameMode = {
    val (x, y) = calcDir(d)
    val cell = playField.cell(playerBase.getPlayer.x + x, playerBase.getPlayer.y + y)
    println(cell)
    cell match {
      case Leer() => move(d)
      case Stop() => MapMode(playField, playerBase)
      case f: EnemyCell => t = t.asInstanceOf[MapMode].move(d); startBattle(f)
      case _ => MapMode(playField, playerBase)
    }
  }

  def startBattle(enemys: EnemyCell): GameMode = {
    enemy = enemys
    println("start battle")
    handle(StartCombat)//CombatMode(playerBase, enemy)
  }

  override def toString: String = playField.toString

  override def cell(x: Int, y: Int): Cell = playField.cell(x,y)

  override def  playlist: PlayerList = playerBase

}

