package de.htwg.se.heroes.model.fieldComponent

case class EnemyCell(strength: Int) extends Cell {
  override val typ  = "F"
}
