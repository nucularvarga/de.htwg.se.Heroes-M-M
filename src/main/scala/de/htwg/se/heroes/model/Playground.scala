package de.htwg.se.heroes.model

class Playground(var playfield: Field) {

  def this(size: Int) = this(new Field(size))
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

    playfield
  }


  def move(row: Int, col: Int): Field = {
    playfield = playfield.replaceCell(playerbase(player).x, playerbase(player).y, Leer())
    playerbase = playerbase.updated(player, playerbase(player).walk(row, col))
    playfield.replaceCell(row, col, HeroCell((player + 1).toString))
  }

  def showstats(i: Int): Boolean = {
      println(playerbase(i).toString)
      false
  }


  def evalInput(input: String): Playground = {
    if(goodmove(input)) {
      player = (player + 1) % playerbase.length
      println("Spieler " + playerbase(player).name + " ist dran")
    } else {
      println("Spieler " + playerbase(player).name + " ist wieder dran")
    }
      this
  }


  def goodmove(dir: String): Boolean ={
    var cell = new Cell()
    var x = 0
    var y = 0
    dir match {
      case "w" =>  x = - 1
      case "a" => y = -1
      case "s" => x = 1
      case "d" => y = 1
      case "t" => showstats(player); false;  //OHJEHJE
      case _ => println("error"); false;
    }
    cell = playfield.cell(playerbase(player).x + x , playerbase(player).y + y)
    cell match {
      case Stop() => println("nicht begehbar"); false
      case l:Leer  => playfield = move(playerbase(player).x + x , playerbase(player).y + y); true
      case e:EnemyCell => playfield = attack(playerbase(player).x + x , playerbase(player).y + y, e.strength); true
      case _ => println("Kein gültiger Zug");false
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
    playfield.toString
  }

}
