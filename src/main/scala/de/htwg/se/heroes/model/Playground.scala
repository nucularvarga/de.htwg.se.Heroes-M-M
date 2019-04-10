package de.htwg.se.heroes.model

class Playground(playfield: Field) {
  var playfield = this(new Field(5))
  val playerbase: List[Player] = List(new Player("1", 0, 1, 1, 1), new Player("2", 0, 2, 2, 2))

  def initplayground: Field = {
    playfield.init
  }


  def gameStart: Unit = {
    //var field = new Field(5)
    var x = false
    while (true) {
      for (a <- playerbase) {
        println("Spieler " + a.name + " ist dran")
        var input = scala.io.StdIn.readLine()
        while(!goodmove(input)) {
            println("Spieler " + a.name + " ist dran")
            input = scala.io.StdIn.readLine()
          }
        }
      }
    }

  def move(cell: Cell): Boolean = {
    setCell(playerbase(1).getX, playerbase(1).getY, "Leer")
    setCell(cell, "Player")
    true
  }

  def goodmove(dir: String): Boolean = {
    dir match {
      case "w" => checkmove(0)
      case "a" => checkmove(1)
      case "s" => checkmove(2)
      case "d" => checkmove(3)
      case _ => false
    }
  }

  def checkmove(dir: Int): Boolean ={
    var cell = new Cell()
    dir match {
      case 0 =>  cell = getCell(playerbase(1).getY + 1, playerbase(1).getX)
      case 1 => cell = getCell(playerbase(1).getX - 1, playerbase(1).getY)
      case 2 => cell = getCell(playerbase(1).getY - 1, playerbase(1).getX)
      case 3 => cell = getCell(playerbase(1).getX + 1, playerbase(1).getY)
      case _ => println("error")
    }

    cell match {
      case Stop() => false
      case Leer()  => move(cell)
      case _ => println("eerror");false
    }
  }

  def getCell(x: Int, y: Int): Cell ={
  //  playfield.field(x)(y)
  }

  def setCell(x: Int, y: Int, name: String): Unit ={
   // playfield.field(x)(y)
  }

  def setCell(cell: Cell, name: String): Unit =
  {

  }

}
