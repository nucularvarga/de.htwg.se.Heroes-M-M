package de.htwg.se.heroes.model.fieldComponent

import javax.inject.Inject

//TODO DECORATOR
class Cell @Inject()() {
val typ: String = " "

override def toString: String = typ
}