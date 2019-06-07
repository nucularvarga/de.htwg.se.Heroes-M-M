package de.htwg.se.heroes.controller

object UIEvent extends Enumeration {
  type UIEvent = Value
  val StartCombat, MoveUp, MoveDown, MoveRight, MoveLeft, WinEndCombat, LoseEndCombat = Value
}

object GameMode extends Enumeration {
  type GameMode = Value
  val Map, Combat = Value
}

import UIEvent._
import de.htwg.se.heroes.model.{Cell, Matrix, PlayerList}

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

  def updatePlayerBase(base: PlayerList): GameMode

  def playlist: PlayerList


}


