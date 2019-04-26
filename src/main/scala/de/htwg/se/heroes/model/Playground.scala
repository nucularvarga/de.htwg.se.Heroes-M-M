package de.htwg.se.heroes.model

class Playground(var playfield: Field) {

  def this(size: Int) = this(new Field(size))

  var errorString = ""

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
      playfield = playfield.replaceCell(4, 4, EnemyCell(1))
    playfield
  }


  def move(row: Int, col: Int): Field = {
    playfield = playfield.replaceCell(playerbase(player).x, playerbase(player).y, Leer())
    playerbase = playerbase.updated(player, playerbase(player).walk(row, col))
    playfield.replaceCell(row, col, HeroCell((player + 1).toString))
  }

  def showstats: String = {
      playerbase(player).toString
  }

  def nextplayerturn: Player = {
    playerbase(0)
  }

  def evalInput(input: String): Playground = {
    if(goodmove(input)) {
      player = (player + 1) % playerbase.length
    }
      this
  }

  def msgturn: String = {
    "Spieler " + playerbase(player).name + " ist dran"
  }

  def msgstatus(input: String): String = {
    input match {
      case "t" => showstats
      case _ => ""
    }
  }

  def calcDirection(dir: String): (Int, Int) = {
    dir match {
      case "w" => (-1, 0)
      case "a" => (0, -1)
      case "s" => (1, 0)
      case "d" => (0, 1)
      case _   => (0 ,0)
    }
  }


  def goodmove(dir: String): Boolean ={

    val (x,y) = calcDirection(dir)
    val cell = playfield.cell(playerbase(player).x + x , playerbase(player).y + y)

    cell match {
      case Leer()  => playfield = move(playerbase(player).x + x , playerbase(player).y + y); true
      case e:EnemyCell => playfield = attack(playerbase(player).x + x , playerbase(player).y + y, e.strength); true
      case _ => false
    }
  }

  def attack(row: Int, col: Int, str: Int): Field = {
      if(playerbase(player).strength >= str) {
        playerbase = playerbase.updated(player, playerbase(player).powerUp(10))
        move(row, col)
      } else {
        playfield
      }
  }

  override def toString: String = {
    playfield.toString + errorString + msgturn
  }

}
