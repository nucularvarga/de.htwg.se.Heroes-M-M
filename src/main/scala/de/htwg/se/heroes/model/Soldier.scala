package de.htwg.se.heroes.model

case class Soldier(x: Int, y: Int) extends Cell() {
  val name = "Sap"
  val cost = 10
  val str = 5

  def moveUnit(x: Int, y: Int): Soldier = {
    copy(x, y)
  }

  override val typ  = "S"
}
