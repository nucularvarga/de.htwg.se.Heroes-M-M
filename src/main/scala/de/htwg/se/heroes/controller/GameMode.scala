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

trait GameMode {
  def handle(e: Event): GameMode

  def calcDir(d: Event): (Int, Int) = {
    d match {
      case Event.MoveUp => (-1, 0)
      case Event.MoveLeft => (0, -1)
      case Event.MoveDown => (1, 0)
      case Event.MoveRight => (0, 1)
    }
  }
}


