package de.htwg.se.heroes.model

 case class Field(rows: Vector[Vector[Cell]]) {
  def this(size: Int) = this(Vector.tabulate(size, size){(col, row) => Leer()})


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

  def initField: Field = {
    /* for{
       row <- 0 until size
       col <- 0 until size
     } this = replaceCell(row, col, Leer())
     */
    var f = this
    //map(i=>map(k=>(replaceCell(i,k,Leer()))))

    for(i <- 0 until size) {
      f = f.replaceCell(0, i, Stop())
      f = f.replaceCell(size - 1, i, Stop())
      f = f.replaceCell(i, 0, Stop())
      f = f.replaceCell(i, size - 1, Stop())
    }
    f.replaceCell(4, 4, EnemyCell(1))
  }

}
