package de.htwg.se.heroes.model.soldier.soldierBaseImpl

import de.htwg.se.heroes.model.soldier.SoldierInterface

class MeleeSoldier(x: Int, y: Int) extends Soldier(x, y) with SoldierInterface {
  override val typ: String = "M"
  override val cost = 10
  override val str = 5
  val range = 1

  def getRange: Int = range
}
