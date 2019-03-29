package de.htwg.se.heroes

case class Tile(status: Int) {
   def isMoveable: Boolean = status != 0
}
