package de.htwg.se.heroes.controller

import de.htwg.se.heroes.model._
import de.htwg.se.heroes.util.Observable


object Direction extends Enumeration {
  type Direction = Value
  val Up, Left, Down, Right = Value
}

object GameMode extends Enumeration {
  type GameMode = Value
  val Map, Combat = Value
}

import Direction._
import GameMode._

class Controller(var playField:Field, var playArena:Arena) extends Observable {

  var playerBase = new PlayerList
  val messanger = new Messanger
  var mode: GameMode = GameMode.Map

  def createNewField(size: Int): Unit = {
    playField = new Field(size)
    mode = GameMode.Map
    notifyObservers
  }

  def init(): Unit = {
    playerBase = playerBase.addPlayer("1", 100, 100, 0,  6, 6)
    playerBase = playerBase.addPlayer("2", 100, 100, 0, 3, 3)

    playField = playField.initField
    playArena = playArena.initArena
    playField = playField.set(6, 6, HeroCell("1"))
    playField = playField.set(3, 3, HeroCell("2"))
    notifyObservers
  }

  def action(d : Direction): Unit = {
    if(mode == GameMode.Map) {
      val cell = playField.cell(playerBase.getPlayer.x + calcDirection(d)._1, playerBase.getPlayer.y + calcDirection(d)._2)
      cell match {
        case Leer() => move(d)
        case Stop() =>
        case f: EnemyCell => startBattle(f)
        case _ =>
      }
    } else {
      val cell = playArena.cell(playerBase.getPlayer.x + calcDirection(d)._1, playerBase.getPlayer.y + calcDirection(d)._2)
      cell match {
        case Leer() => move(d)
        case Stop() =>
        case p: HeroCell =>
        case f: Soldier => attack(d, f)
        case _ =>
      }
    }
      notifyObservers
  }


  def startBattle(enemy: EnemyCell): Boolean = {
    playArena = new Arena(10,30)
    mode = GameMode.Combat
    true
  }

  def attack(d: Direction, enemy: Soldier): Boolean = {
      move(d)
      //playerBase = playerBase.updatePlayer(playerBase.getPlayer, 10, calcDirection(d)._1, calcDirection(d)._2)
      //playerBase.nextPlayer // TODO next? iterator?
      messanger.setMsg("Gewonnen")
      true
  }

  def move(d: Direction): Boolean = {
    val (row, col) = calcDirection(d)
    playField = playField.set(playerBase.getPlayer.x, playerBase.getPlayer.y, Leer())
    playField = playField.set(playerBase.getPlayer.x + row, playerBase.getPlayer.y + col, HeroCell(playerBase.getPlayer.name))
    playerBase = playerBase.updatePlayer(playerBase.getPlayer, 0, calcDirection(d)._1, calcDirection(d)._2)
    playerBase.nextPlayer // TODO next? iterator?
    true
  }

  def showStats(): Unit = {
    messanger.setMsg(playerBase.getPlayer.toString)
    notifyObservers
  }

  def openShop(number: Int): Unit = {
    if(playerBase.getPlayer.gold > number * Soldier().cost) {
      playerBase = playerBase.setUnits(number, number * Soldier().cost)
      messanger.setMsg("Erfolgreich gekauft")
    } else messanger.setMsg("Nicht genug gold")
    notifyObservers
  }

  def calcDirection(d: Direction): (Int, Int) = {
    d match {
      case Direction.Up => (-1, 0)
      case Direction.Left => (0, -1)
      case Direction.Down => (1, 0)
      case Direction.Right => (0, 1)
    }
  }


  def playgroundToString: String = {
    if(mode == GameMode.Map)
      playField.toString + messanger.getMsg
    else
      playArena.toString + "test arnea"
  }
}
