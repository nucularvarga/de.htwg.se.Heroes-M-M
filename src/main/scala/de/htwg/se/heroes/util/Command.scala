package de.htwg.se.heroes.util

import de.htwg.se.heroes.controller.GameMode

trait Command {

  def doStep:Unit
  def undoStep:Unit
  def redoStep:Unit

}
