package de.htwg.se.heroes.model

case class Arena(cells: Matrix) {
  def this(row: Int, col: Int) = this(new Matrix(row, col))


  val length: Int = cells.length
  val height: Int = cells.height

  def cell(row: Int, col: Int): Cell = cells.cell(row, col)

  def set(row: Int, col: Int, cell: Cell): Arena = copy(cells.replaceCell(row, col, cell))

  override def toString: String = {
    var box = (("G" * length) + "\n") * height
    for {
      row <- 0 until height
      col <- 0 until length
    } box = box.replaceFirst("G", cell(row, col).toString)
    box
  }

  def initArena: Arena = {
    /* for{
       row <- 0 until size
       col <- 0 until size
     } this = replaceCell(row, col, Leer())
     */
    //map(i=>map(k=>(replaceCell(i,k,Leer()))))
    var f = this
    for(i <- 0 until height) {
      f = f.set(i, 0, Stop())
      f = f.set(i, length - 1, Stop())
    }
    for(i <- 0 until length) {
      f = f.set(0, i, Stop())
      f = f.set(height - 1, i, Stop())
    }

    f.set(4, 4, EnemyCell(1))
  }

}