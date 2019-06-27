package de.htwg.se.heroes.model.soldier.soldierBaseImpl

import de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl.Cell

case class Soldier(x: Int, y: Int) extends Cell() {
  override val typ  = "S"
  val cost = 10
  val str = 5

  def getTyp: String = typ
  def getCost: Int = cost
  def getStrength: Int = str

}
