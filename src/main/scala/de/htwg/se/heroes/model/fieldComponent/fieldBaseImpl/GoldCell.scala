package de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl

case class GoldCell() extends Cell(){
  override val typ  = "G"
  val gold = 50
}

