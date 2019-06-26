package de.htwg.se.heroes.model

import de.htwg.se.heroes.controllerComponent.controllerBaseImpl.gamemode.GameMode
//import de.htwg.se.heroes.model.Visitor

trait VisitableElement {
  def accept(visitor: Visitor): Unit
}
