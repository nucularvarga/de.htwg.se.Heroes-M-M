package de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl

import de.htwg.se.heroes.model.fieldComponent.CellInterface

//TODO DECORATOR
class Cell() extends CellInterface  {
val typ: String = " "

  override def value: String = typ

override def toString: String = typ
}