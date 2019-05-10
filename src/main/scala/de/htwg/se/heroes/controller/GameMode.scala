package de.htwg.se.heroes.controller

object Event extends Enumeration {
  type Event = Value
  val StartCombat, EndCombat = Value
}

import Event._

trait GameMode {
  def handle(e: Event): GameMode
}


