package de.htwg.se.heroes.model.fieldComponent

import javax.inject.Inject

//TODO DECORATOR
class Cell() extends CellInterface  {
val typ: String = " "

  override def value: String = typ

override def toString: String = typ
}