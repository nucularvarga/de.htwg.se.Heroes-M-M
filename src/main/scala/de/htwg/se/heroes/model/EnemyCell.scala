package de.htwg.se.heroes.model

case class EnemyCell(override val typ: String, strength: Int) extends Cell(typ) {

}
