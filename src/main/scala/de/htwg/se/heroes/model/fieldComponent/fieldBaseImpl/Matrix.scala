package de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl

import de.htwg.se.heroes.model.fieldComponent.MatrixInterface

case class Matrix(rows: Vector[Vector[Cell]]) extends MatrixInterface {
  def this(size: Int) = this(Vector.tabulate(size, size) { (y, x) => Leer() })

  def this(x: Int, y: Int) = this(Vector.tabulate(y, x) { (y, x) => Leer() })
  val length: Int = rows(0).length
  val height: Int = rows.length

  def cell(x: Int, y: Int): Cell = rows(y)(x)

  def replaceCell(x: Int, y: Int, cell: Cell): Matrix = copy(rows.updated(y, rows(y).updated(x, cell)))

}
