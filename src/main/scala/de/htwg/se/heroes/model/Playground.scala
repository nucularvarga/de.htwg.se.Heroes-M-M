package de.htwg.se.heroes.model


import de.htwg.se.heroes.controller.Direction
import de.htwg.se.heroes.controller.Direction._

class Playground(var playfield: Field) {

  def this(size: Int) = this(new Field(size))

  var errorString = ""
  var msgContainer = Vector.empty[String]
  var playerbase = Vector.empty[Player]
  var player = 0

  def setPlayer(name: String, gold: Int, str: Int, row: Int, col: Int): Vector[Player] = playerbase :+ Player(name, gold, str, row, col)

  def replaceField(row: Int, col: Int, cell: Cell): Field = playfield.replaceCell(row, col, cell)

  def init: Field = {
    for{
      row <- 0 until playfield.size
      col <- 0 until playfield.size
    } playfield = playfield.replaceCell(row, col, Leer())

    for(i <- 0 until playfield.size  ) {
      playfield = replaceField(0, i, Stop())
      playfield = replaceField(playfield.size - 1, i, Stop())
      playfield = replaceField(i, 0, Stop())
      playfield = replaceField(i, playfield.size - 1, Stop())
    }
     playfield.replaceCell(4, 4, EnemyCell(1))
  }


  def move(d: Direction): Field = {
    val (row, col) = calcDirection(d)
    playfield = playfield.replaceCell(playerbase(player).x, playerbase(player).y, Leer())
    playerbase = playerbase.updated(player, playerbase(player).walk(row, col))
    println()
    playfield.replaceCell(playerbase(player).x, playerbase(player).y, HeroCell((player + 1).toString))
  }

  def showstats: String = {
      playerbase(player).toString
  }

  def nextplayerturn: Player = {
    playerbase(0)
  }

  def addmsg(msg: String): Vector[String] = {
    //"Spieler " + playerbase(player).name + " ist dran"
    msgContainer :+ msg
  }

  def msgstatus(input: String): String = {
    input match {
      case "t" => showstats
      case _ => ""
    }
  }

  def calcDirection(d: Direction): (Int, Int) = {
    d match {
      case Direction.Up => (-1, 0)
      case Direction.Left => (0, -1)
      case Direction.Down => (1, 0)
      case Direction.Right => (0, 1)
    }
  }


  def goodmove(dir: Direction): Boolean ={

    val (x,y) = calcDirection(dir)
    val cell = playfield.cell(playerbase(player).x + x , playerbase(player).y + y)

    cell match {
      case Stop()  => false
      case _ => true
    }
  }

  def attack(row: Int, col: Int, str: Int): Field = {
      if(playerbase(player).strength >= str) {
        playerbase = playerbase.updated(player, playerbase(player).powerUp(10))
        move(Direction.Up)
      } else {
        playfield
      }
  }

  def makeString(): String = {
      msgContainer.mkString("\n")
  }

  override def toString: String = {
    playfield.toString
  }

}
