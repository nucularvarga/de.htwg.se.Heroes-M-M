package de.htwg.se.heroes.model


import de.htwg.se.heroes.controller.Direction
import de.htwg.se.heroes.controller.Direction._

class Playground(var playfield: Field) {

  def this(size: Int) = this(new Field(size))

  var msg = ""
  var errorString = ""
  var msgContainer = Vector.empty[String]


  def replaceField(row: Int, col: Int, cell: Cell): Field = playfield.replaceCell(row, col, cell)

  def getCell(row: Int, col: Int) : Cell = playfield.cell(row, col)

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


  def move(d: Direction, player:Player): Playground = {
    val (row, col) = calcDirection(d)
    playfield = playfield.replaceCell(player.x, player.y, Leer())
    playfield = playfield.replaceCell(player.x + row, player.y + col, HeroCell(player.name))
    this
  }

  def showstats(player:Player): String = {
    player.toString
  }

  def addmsg(msg: String): Vector[String] = {
    //"Spieler " + playerbase(player).name + " ist dran"
    msgContainer :+ msg
  }



  def calcDirection(d: Direction): (Int, Int) = {
    d match {
      case Direction.Up => (-1, 0)
      case Direction.Left => (0, -1)
      case Direction.Down => (1, 0)
      case Direction.Right => (0, 1)
    }
  }


  def goodmove(dir: Direction, player:Player): Boolean ={

    val (x,y) = calcDirection(dir)
    val cell = playfield.cell(player.x + x , player.y + y)

    cell match {
      case Stop()  => false
      case _ => true
    }
  }

  def attack(row: Int, col: Int, str: Int, player:Player): Field = {
      if(player.strength >= str) {
        //playerbase = playerbase.updated(player, playerbase(player).powerUp(10)) TODO
      //  move(Direction.Up)
        playfield
      } else {
        playfield
      }
  }

  def makeString(): String = {
      msgContainer.mkString("\n")
  }

  override def toString: String = playfield.toString

}
