package de.htwg.se.heroes.model

case class Soldier(x: Int, y: Int) extends Cell() {
  val name = "Sap"
  val cost = 10
  val str = 5

  def moveUnit(xn: Int, yn: Int): Soldier = {
    copy(x + xn, y + yn)
  }

  override val typ  = "S"
}
