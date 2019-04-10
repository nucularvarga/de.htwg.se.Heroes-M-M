package de.htwg.se.heroes.model

class Playground {
  val spielerarray = List(0, 1, 2, 3)
  def gameStart: Unit = {
    //var field = new Field(5)

    var x = false
    while (true) {
      for (a <- spielerarray) {
        println("Spieler " + a + " ist dran")
        var input = scala.io.StdIn.readLine()
        while(!goodmove(input)) {
            println("Spieler " + a + " ist dran")
            input = scala.io.StdIn.readLine()
          }
        }
      }
    }

  def move(cell: Cell): Boolean = {
    setCell(spielerarray(1).getX(), spielerarray(1).getY(), "Leer")
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
    var cell = Leer()
    dir match {
      case 0 =>  cell = getCell(spielerarray(1).getY() + 1, spielerarray(1).getX())
      case 1 => cell = getCell(spielerarray(1).getX() - 1, spielerarray(1).getY())
      case 2 => cell = getCell(spielerarray(1).getY() - 1, spielerarray(1).getX())
      case 3 => cell = getCell(spielerarray(1).getX() + 1, spielerarray(1).getY())
      case _ => println("error")
    }

    cell match {
      case Stop() => false
      case Leer()  => move(cell)
    }
  }

  def getCell(x: Int, y: Int): Cell ={
    Field(x)(y)
  }

  def setCell(x: Int, y: Int, name: String): Unit ={
    Field(x)(y)
  }

  def setCell(cell: Cell, name: String): Unit =
  {

  }

}
