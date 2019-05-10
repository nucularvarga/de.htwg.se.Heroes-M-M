package de.htwg.se.heroes.model

 case class Field(cells: Matrix) {
  def this(size: Int) = this(new Matrix(size))


  val size: Int = cells.length

  def cell(row: Int, col: Int): Cell = cells.cell(row, col)

  def set(row: Int, col: Int, cell: Cell): Field = copy(cells.replaceCell(row, col, cell))

  override def toString: String = {
    var box = (("G" * size) + "\n") * size
    for {
      row <- 0 until size
      col <- 0 until size
    } box = box.replaceFirst("G", cell(row, col).toString)
    box
  }

  def initField: Field = {
    var f = this
    for(i <- 0 until size) {
      f = f.set(0, i, Stop())
      f = f.set(size - 1, i, Stop())
      f = f.set(i, 0, Stop())
      f = f.set(i, size - 1, Stop())
    }
    f.set(3, 3, EnemyCell(1))
  }

}
