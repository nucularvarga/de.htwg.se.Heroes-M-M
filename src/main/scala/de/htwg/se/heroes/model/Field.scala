package de.htwg.se.heroes.model

case class Field(rows: Vector[Vector[Cell]]) {
  def this(size: Int) = this(Vector.tabulate(size, size){(col, row) => new Cell()})

  val size: Int = rows.size

  def cell(row: Int, col: Int): Cell = rows(row)(col)

  def replaceCell(row: Int, col: Int, cell: Cell): Field = copy(rows.updated(row, rows(row).updated(col, cell)))

  override def toString: String = {
    var box = (("G" * size) + "\n") * size
    for {
      row <- 0 until size
      col <- 0 until size
    } box = box.replaceFirst("G", cell(row, col).toString)
    box
  }

}
