package de.htwg.se.heroes.model

case class Matrix(rows: Vector[Vector[Cell]]) {
  def this(size: Int) = this(Vector.tabulate(size, size) { (col, row) => Leer() })

  def this(row: Int, col: Int) = this(Vector.tabulate(row, col) { (col, row) => Leer() })
  val length: Int = rows(0).length
  val height: Int = rows.length

  def cell(row: Int, col: Int): Cell = rows(row)(col)

  def replaceCell(row: Int, col: Int, cell: Cell): Matrix = copy(rows.updated(row, rows(row).updated(col, cell)))

}