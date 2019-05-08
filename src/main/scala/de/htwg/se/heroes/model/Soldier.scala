package de.htwg.se.heroes.model

case class Soldier(row: Int, col: Int) extends Cell() {
  val name = "Sap"
  val cost = 10
  val str = 5

  override val typ  = "S"
}
