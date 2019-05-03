package de.htwg.se.heroes.controller

import de.htwg.se.heroes.model._
import de.htwg.se.heroes.util.Observable


object Direction extends Enumeration {
  type Direction = Value
  val Up, Left, Down, Right = Value
}

import Direction._

class Controller(var playground:Field) extends Observable {

  var playerBase = new PlayerList

  def createNewField(size: Int): Unit = {
    playground = new Field(size)
    notifyObservers
  }

  def init(): Unit = {
    playerBase = playerBase.addPlayer("1", 100, 100, 6, 6)
    playerBase = playerBase.addPlayer("2", 100, 100, 3, 3)


    playground = playground.initField
    playground = playground.replaceCell(6, 6, HeroCell("1"))
    playground = playground.replaceCell(3, 3, HeroCell("2"))
    notifyObservers
  }


  def action(d : Direction): Unit = {
    val cell = playground.cell(playerBase.getPlayer.x + calcDirection(d)._1, playerBase.getPlayer.y + calcDirection(d)._2)
    val good = cell match {
      case Leer() => move(d)
      case Stop() => false
      case p:HeroCell => false
      case f:EnemyCell => attack(d, f)
      case _ => false
    }

    if(!good) {
      //playground.msg = "Error" TODO msg
    }

      notifyObservers
  }

  def attack(d: Direction, enemy: EnemyCell): Boolean = {
    //val boool = startbattle() TODO battle
    if(playerBase.getPlayer.strength >= enemy.strength) {
      move(d)
      //playerBase = playerBase.updatePlayer(playerBase.getPlayer, 10, calcDirection(d)._1, calcDirection(d)._2)
      //playerBase.nextPlayer // TODO next? iterator?
      //playground.msg = "Gewonnen"
      true
    } else {
      //playground.msg = "Verloren"
      false
    }
  }

  def move(d: Direction): Boolean = {
    val (row, col) = calcDirection(d)
    playground = playground.replaceCell(playerBase.getPlayer.x, playerBase.getPlayer.y, Leer())
    playground = playground.replaceCell(playerBase.getPlayer.x + row, playerBase.getPlayer.y + col, HeroCell(playerBase.getPlayer.name))
    playerBase = playerBase.updatePlayer(playerBase.getPlayer, 0, calcDirection(d)._1, calcDirection(d)._2)
    playerBase.nextPlayer // TODO next? iterator?
    true
  }
/*
  def showStats(): Unit = {
    playground.msg = playground.showstats(playerBase.getPlayer)
    notifyObservers
  }
*/

  def calcDirection(d: Direction): (Int, Int) = {
    d match {
      case Direction.Up => (-1, 0)
      case Direction.Left => (0, -1)
      case Direction.Down => (1, 0)
      case Direction.Right => (0, 1)
    }
  }


  def playgroundToString: String = playground.toString //+ playground.msg
}
