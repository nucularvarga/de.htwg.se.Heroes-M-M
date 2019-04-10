package de.htwg.se.heroes.model

case class Field(rows: Vector[Vector[Cell]]) {
  def this(size: Int) = this(Vector.tabulate(size, size){(col, row) => (new Cell())})

  val size: Int = rows.size

  def init: Field = {
    copy(rows.updated(1, rows(1).updated(2, Leer())))
  }

}
