package de.htwg.se.heroes.model.soldier.soldierBaseImpl

import de.htwg.se.heroes.model.soldier.SoldierInterface

class RangeSoldier(x: Int, y: Int) extends Soldier(x,y) with SoldierInterface {
  override val typ: String = "R"
  override val cost = 40
  override val str = 10
  val range = 1

  override def getRange: Int = range
}
