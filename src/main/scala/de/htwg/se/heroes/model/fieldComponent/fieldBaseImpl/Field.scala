package de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl

import com.google.inject.Inject
import de.htwg.se.heroes.model.fieldComponent.FieldInterface

case class Field @Inject()(cells: Matrix) extends FieldInterface {
 def this(size: Int) = this(new Matrix(size))


 val size: Int = cells.length

 def cell(x: Int, y: Int): Cell = cells.cell(x, y)

 def set(x: Int, y: Int, cell: Cell): Field = copy(cells.replaceCell(x, y, cell))

 override def toString: String = {
   var box = (("G" * size) + "\n") * size
   for {
     y <- 0 until size
     x <- 0 until size
   } box = box.replaceFirst("G", cell(x, y).toString)
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
   f
 }

}
