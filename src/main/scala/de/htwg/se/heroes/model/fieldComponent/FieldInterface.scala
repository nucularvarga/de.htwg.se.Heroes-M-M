package de.htwg.se.heroes.model.fieldComponent

import de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl.Cell


trait FieldInterface {
  def cell(x: Int, y: Int): Cell
  def size: Int
  def set(x: Int, y: Int, cell: Cell): FieldInterface
  def initField: FieldInterface
}

trait MatrixInterface {
  def cell(x: Int, y: Int): Cell
  def replaceCell(x: Int, y: Int, cell: Cell): MatrixInterface
}

trait ArenaInterface {
  def cell(x: Int, y: Int): Cell
  def size: (Int, Int)
  def set(x: Int, y: Int, cell: Cell): ArenaInterface
  def initArena: ArenaInterface
}

trait CellInterface {
  def value: String
}