package de.htwg.se.heroes.model.soldier.soldierBaseImpl

case class MeleeSoldier(override val x: Int, override val y: Int) extends Soldier(x, y) {
  override val typ: String = "M"
  override val cost = 10
  override val str = 5
  val range = 1
}
