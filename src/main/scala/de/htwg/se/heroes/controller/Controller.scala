package de.htwg.se.heroes.controller

import de.htwg.se.heroes.model._
import de.htwg.se.heroes.util.Observable


object Direction extends Enumeration {
  type Direction = Value
  val Up, Left, Down, Right = Value
}

import Direction._

class Controller(var playground:Playground) extends Observable {

  var playerBase = new PlayerList

  def createNewPlayground(size: Int): Unit = {
    playground = new Playground(size)
    playerBase = playerBase.addPlayer("1", 100, 100, 6, 6)
    playerBase = playerBase.addPlayer("2", 100, 100, 3, 3)


    playground.playfield = playground.init
    playground.playfield = playground.replaceField(6, 6, HeroCell("1"))
    playground.playfield = playground.replaceField(3, 3, HeroCell("2"))

    notifyObservers
  }

  def action(d : Direction): Unit = {
    if(playground.goodmove(d, playerBase.getPlayer)) {
      val cell = playground.getCell(playerBase.getPlayer.x + playground.calcDirection(d)._1, playerBase.getPlayer.y + playground.calcDirection(d)._2)
      cell match {
        case Leer() => move(d)
        case Stop() =>
        case p:HeroCell =>
        case f:EnemyCell => attack(d, f)
        case _ =>
      }
      notifyObservers
    }
  }

  def attack(d: Direction, enemy: EnemyCell): Unit = {
    if(playerBase.getPlayer.strength >= enemy.strength) {
      playground = playground.move(d, playerBase.getPlayer)
      playerBase = playerBase.updatePlayer(playerBase.getPlayer, 10, playground.calcDirection(d)._1, playground.calcDirection(d)._2)
      playerBase.nextPlayer // TODO next? iterator?
    }
  }

  def move(d: Direction): Unit = {
    playground = playground.move(d, playerBase.getPlayer)
    playerBase = playerBase.updatePlayer(playerBase.getPlayer, 0, playground.calcDirection(d)._1, playground.calcDirection(d)._2)
    playerBase.nextPlayer // TODO next? iterator?
  }

  def showStats(): Unit = {
    playground.msg = playground.showstats(playerBase.getPlayer)
    notifyObservers
  }

  def playgroundToString: String = playground.toString + playground.msg
}
