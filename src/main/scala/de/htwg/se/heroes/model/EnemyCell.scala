package de.htwg.se.heroes.model

case class EnemyCell(strength: Int) extends Cell() {
  override val typ  = "F"
}
