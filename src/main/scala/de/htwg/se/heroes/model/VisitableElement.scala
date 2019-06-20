package de.htwg.se.heroes.model

import de.htwg.se.heroes.controllerComponent.UIEvent.UIEvent
import de.htwg.se.heroes.controllerComponent.GameMode
//import de.htwg.se.heroes.model.Visitor

trait VisitableElement {
  def accept(visitor: Visitor): Unit
}
