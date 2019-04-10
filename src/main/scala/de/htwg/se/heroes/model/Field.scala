package de.htwg.se.heroes.model

class Field(size: Int) {
  val field: Vector[Vector[Cell]] = Vector.fill(size, size)(new Cell())
}
