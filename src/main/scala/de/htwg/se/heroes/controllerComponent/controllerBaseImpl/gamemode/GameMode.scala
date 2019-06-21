package de.htwg.se.heroes.controllerComponent.controllerBaseImpl.gamemode

object UIEvent extends Enumeration {
  type UIEvent = Value
  val StartCombat, MoveUp, MoveDown, MoveRight, MoveLeft, WinEndCombat, LoseEndCombat, BuyMelee, BuyRange = Value
}

object GameMode extends Enumeration {
  type GameMode = Value
  val Map, Combat = Value
}

import de.htwg.se.heroes.controllerComponent.controllerBaseImpl.gamemode.UIEvent._
import de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl.Cell
import de.htwg.se.heroes.model.playerComponent.PlayerListInterface

trait GameMode{

  var saveMap = MapMode

  def handle(e: UIEvent): GameMode

  def calcDir(d: UIEvent): (Int, Int) = {
    d match {
      case UIEvent.MoveUp => (0, -1)
      case UIEvent.MoveLeft => (-1, 0)
      case UIEvent.MoveDown => (0, 1)
      case UIEvent.MoveRight => (1, 0)
    }
  }

  def cell(x: Int, y: Int): Cell

  def updatePlayerBase(base: PlayerListInterface): GameMode

  def playlist: PlayerListInterface


}


