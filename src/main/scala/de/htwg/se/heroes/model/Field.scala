package de.htwg.se.heroes.model

case class Field(rows: Vector[Vector[Cell]]) {
  def this(size: Int) = this(Vector.tabulate(size, size){(col, row) => new Cell()})

  val size: Int = rows.size

  def cell(row: Int, col: Int): Cell = rows(row)(col)

  def replaceCell(row: Int, col: Int, cell: Cell): Field = copy(rows.updated(row, rows(row).updated(col, cell)))

  def fieldprint: Unit = {
    for(i <- 0 to size - 1){
      for(k <- 0 to size -1) {
        print(rows(i)(k).toString)
      }
      println()
    }
  }

}
