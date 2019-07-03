package de.htwg.se.heroes.model.soldier.soldierBaseImpl

import de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl.Cell
import de.htwg.se.heroes.model.soldier.SoldierInterface

class Soldier(x: Int, y: Int) extends Cell() with SoldierInterface {
  override val typ  = "S"
  val cost = 10
  val str = 5

  override def getRange: Int = 1
  def getTyp: String = typ
  def getCost: Int = cost
  def getStrength: Int = str
  def getX: Int = x
  def getY: Int = y

}
