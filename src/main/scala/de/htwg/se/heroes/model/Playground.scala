package de.htwg.se.heroes.model


import de.htwg.se.heroes.controller.Direction
import de.htwg.se.heroes.controller.Direction._

class Playground(var playfield: Field) {

  def this(size: Int) = this(new Field(size))

  var msg = ""
  var errorString = ""
  var msgContainer = Vector.empty[String]


  def replaceField(row: Int, col: Int, cell: Cell): Field = playfield.replaceCell(row, col, cell)

  def getCell(row: Int, col: Int) : Cell = playfield.cell(row, col)





  override def toString: String = playfield.toString

}
