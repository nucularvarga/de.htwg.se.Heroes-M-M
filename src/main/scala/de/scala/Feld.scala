package de.scala

case class Feld(val rows: Vector[Vector[Tile]]) {
  def this(size:Int) = this(Vector.tabulate(size, size){(row, col) => new Tile(0)})
  def replaceCell(row:Int, col:Int, cell:Tile):Feld = copy(rows.updated(row, rows(row).updated(col, cell)))
  def printf:Unit = {
    for (n <- 0.to(9)) {
      for (m <- 0.to(9)) print(rows(n)(m))
      println()
    }
  }
}
