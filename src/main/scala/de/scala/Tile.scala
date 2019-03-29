package de.scala

case class Tile(status: Int) {
   def isMoveable: Boolean = status != 0
}