package de.htwg.se.heroes.controller

import de.htwg.se.heroes.controller.Event.Event

case class MapMode() extends GameMode {
  println("MapMode activ")
  override def handle(e: Event):GameMode = {
    e match {
      case Event.StartCombat => CombatMode()
      case Event.EndCombat => MapMode()
    }
  }
}


}
