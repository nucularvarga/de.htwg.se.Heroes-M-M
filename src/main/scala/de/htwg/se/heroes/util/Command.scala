package de.htwg.se.heroes.util

trait Command {

  def doStep:Unit
  def undoStep:Unit
  def redoStep:Unit

}
