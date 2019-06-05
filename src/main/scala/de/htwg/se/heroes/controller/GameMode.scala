package de.htwg.se.heroes.controller

object Event extends Enumeration {
  type Event = Value
  val StartCombat, EndCombat, MoveUp, MoveDown, MoveRight, MoveLeft = Value
}

object GameMode extends Enumeration {
  type GameMode = Value
  val Map, Combat = Value
}

import Event.{Event, _}
import de.htwg.se.heroes.model.{Cell, Matrix}

trait GameMode{

  def handle(e: Event): GameMode

  def calcDir(d: Event): (Int, Int) = {
    d match {
      case Event.MoveUp => (0, -1)
      case Event.MoveLeft => (-1, 0)
      case Event.MoveDown => (0, 1)
      case Event.MoveRight => (1, 0)
    }
  }

  def cell(x: Int, y: Int): Cell


}


