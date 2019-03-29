package de.htwg

case class Tile(status: Int) {
   def isMoveable: Boolean = status != 0
}
