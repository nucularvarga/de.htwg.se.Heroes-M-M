package de.htwg.se.heroes.model

import de.htwg.se.heroes.controller.UIEvent.UIEvent
import de.htwg.se.heroes.controller.GameMode
import de.htwg.se.heroes.model.Visitor

trait VisitableElement {
  def accept(visitor: Visitor): Unit
}
