package de.htwg.se.heroes.model

class Playground {
  val spielerarray = List(0, 1, 2, 3)
  def main(args: Array[String]): Unit = {
    //var field = new Field(5)

    var x = false
    for (a <- spielerarray) {
      println("Spieler " + a + " ist dran")
      val b = scala.io.StdIn.readLine()
      b match {
        case "w" => x = move(0)
        case "a" => x = move(1)
        case "s" => x = move(2)
        case "d" => x = move(3)
        case  _  => println(b + "ist keine gültige Eingabe")
      }
      if(!x) {
        println(b + "ist kein gültiger zug")
      }
    }
  }

  def move(dir: Int): Boolean ={

    dir match {
      case 0 => val cell = getCell(spielerarray(1).getY() + 1, val x = spielerarray(1).getX())
      case 1 => val cell = getCell(spielerarray(1).getX() - 1 val x = spielerarray(1).getY())
      case 2 => val cell = getCell(spielerarray(1).getY() - 1 val x = spielerarray(1).getX())
      case 3 => val cell = getCell(spielerarray(1).getX() + 1 val x = spielerarray(1).getY())
      case _ => println("error")
    }

    cell match {
      case Stop => false
      case  _   => true
    }
  }

  def getCell(x: Int, y: Int): Cell ={
    Field(x)(y)
  }

}
