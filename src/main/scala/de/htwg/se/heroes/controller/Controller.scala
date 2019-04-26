package de.htwg.se.heroes.controller

import de.htwg.se.heroes.model._
import de.htwg.se.heroes.util.Observable


object Direction extends Enumeration {
  type Direction = Value
  val Up, Left, Down, Right = Value
}

import Direction._

class Controller(var playground:Playground) extends Observable {

  def createNewPlayground(size: Int): Unit = {
    playground = new Playground(size)
    playground.playfield = playground.init
    playground.playerbase = playground.setPlayer("Kevin", 50, 100, 1, 1)
    playground.playfield = playground.replaceField(1, 1, HeroCell("1"))
    playground.playerbase = playground.setPlayer("Janko", 50, 100, 3, 3)
    playground.playfield = playground.replaceField(3, 3, HeroCell("2"))
    notifyObservers
  }

  def move(d: Direction): Unit = {
    if(playground.goodmove(d)) {
      playground.playfield = playground.move(d)
    }
    notifyObservers
  }

  def showStats(): Unit = {

  }

  def playgroundToString: String = playground.toString
}
