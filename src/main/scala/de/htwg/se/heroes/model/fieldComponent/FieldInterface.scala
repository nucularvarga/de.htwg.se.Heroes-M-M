package de.htwg.se.heroes.model.fieldComponent


trait FieldInterface {
  def cell(x: Int, y: Int): Cell
  def set(x: Int, y: Int, cell: Cell): FieldInterface
  def initField: FieldInterface
}

trait MatrixInterface {
  //def Matrix(size: Int): MatrixInterface
  //def Matrix(x: Int, y: Int): MatrixInterface
  def cell(x: Int, y: Int): Cell
  def replaceCell(x: Int, y: Int, cell: Cell): MatrixInterface
}

trait ArenaInterface {
  def cell(x: Int, y: Int): Cell
  def size: (Int, Int)
  def set(x: Int, y: Int, cell: Cell): ArenaInterface
  def initArena: ArenaInterface
  //def Arena(x: Int, y: Int): ArenaInterface
}

trait CellInterface {
  def value: String
}