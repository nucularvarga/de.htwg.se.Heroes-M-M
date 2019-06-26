package de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl

import com.google.inject.Inject
import de.htwg.se.heroes.model.fieldComponent.ArenaInterface

case class Arena @Inject()(cells: Matrix) extends ArenaInterface {
  def this (x: Int, y: Int) = this(new Matrix(x, y))


  val length: Int = cells.length
  val height: Int = cells.height

  override def size: (Int, Int) = (length, height)

  def cell(x: Int, y: Int): Cell = cells.cell(x, y)

  def set(x: Int, y: Int, cell: Cell): Arena = copy(cells.replaceCell(x, y, cell))

  override def toString: String = {
    var box = (("G" * length) + "\n") * height
    for {
      y <- 0 until height
      x <- 0 until length
    } box = box.replaceFirst("G", cell(x, y).toString)
    box
  }

  def initArena: Arena = {
    /* for{
       row <- 0 until size
       col <- 0 until size
     } this = replaceCell(row, col, Leer())
     */
    //map(i=>map(k=>(replaceCell(i,k,Leer()))))

    println(length + " " + height)
    var f = this
    for(i <- 0 until height) {
      f = f.set(0, i, Stop())
      f = f.set(length -1 , i, Stop())
    }
    for(i <- 0 until length) {
      f = f.set(i, 0, Stop())
      f = f.set(i, height - 1, Stop())
    }
    f
  }

}
