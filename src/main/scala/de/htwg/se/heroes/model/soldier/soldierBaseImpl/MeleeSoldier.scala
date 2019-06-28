package de.htwg.se.heroes.model.soldier.soldierBaseImpl


class MeleeSoldier(x: Int, y: Int) extends Soldier(x, y) {
  override val typ: String = "M"
  override val cost = 10
  override val str = 5
  val range = 1

  override def getRange: Int = range
}
