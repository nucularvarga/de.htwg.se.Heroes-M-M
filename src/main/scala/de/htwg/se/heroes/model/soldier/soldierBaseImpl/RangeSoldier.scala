package de.htwg.se.heroes.model.soldier.soldierBaseImpl

class RangeSoldier(x: Int, y: Int) extends Soldier(x,y) {
  override val typ: String = "R"
  override val cost = 40
  override val str = 10
  val range = 1

  def getRange: Int = range
}
