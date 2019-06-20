package de.htwg.se.heroes.util

import de.htwg.se.heroes.controllerComponent.GameMode

trait Command {

  def doStep:Unit
  def undoStep:Unit
  def redoStep:Unit

}
