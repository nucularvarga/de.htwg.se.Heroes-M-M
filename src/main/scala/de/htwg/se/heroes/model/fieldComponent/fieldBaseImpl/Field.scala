package de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl

import com.google.inject.Inject
import de.htwg.se.heroes.model.fieldComponent.FieldInterface

case class Field @Inject()(cells: Matrix) extends FieldInterface {
 def this(size: Int) = this(new Matrix(size))


 val sizev = cells.length

  def getMatrix: Matrix = cells

 def cell(x: Int, y: Int): Cell = cells.cell(x, y)

 def set(x: Int, y: Int, cell: Cell): Field = copy(cells.replaceCell(x, y, cell))

  override def size(): Int =  sizev

 override def toString: String = {
   var box = (("J" * sizev) + "\n") * sizev
   for {
     y <- 0 until sizev
     x <- 0 until sizev
   } box = box.replaceFirst("J", cell(x, y).toString)
   box
 }

 def initField: Field = {
   var f = this
   for(i <- 0 until sizev) {
     f = f.set(0, i, Stop())
     f = f.set(sizev - 1, i, Stop())
     f = f.set(i, 0, Stop())
     f = f.set(i, sizev - 1, Stop())
   }
   f
 }

}