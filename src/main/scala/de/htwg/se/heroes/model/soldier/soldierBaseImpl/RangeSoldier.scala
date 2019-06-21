package de.htwg.se.heroes.model.soldier.soldierBaseImpl

case class RangeSoldier(override val x: Int, override val y: Int) extends Soldier(x,y) {
  override val typ: String = "M"
  override val cost = 40
  override val str = 10
  val range = 1
}
