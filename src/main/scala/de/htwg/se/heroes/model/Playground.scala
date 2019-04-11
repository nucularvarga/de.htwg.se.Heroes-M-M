package de.htwg.se.heroes.model

class Playground() {
  var playfield = new Field(10)
  val playerbase: List[Player] = List(new Player("1", 0, 1, 1, 1))


  def prin: Unit = {
    playfield.fieldprint
  }

  def init: Unit = {

    for(i <- 0 to playfield.size - 1){
      for(k <- 0 to playfield.size -1) {
        playfield = playfield.replaceCell(i, k, Leer())
      }
    }

    for(i <- 0 to playfield.size - 1 ) {
      playfield = playfield.replaceCell(0, i, Stop())
    }

    for(i <- 0 to playfield.size - 1 ) {
      playfield = playfield.replaceCell(playfield.size - 1, i, Stop())
    }

    for(i <- 0 to playfield.size - 1 ) {
      playfield = playfield.replaceCell(i, 0, Stop())
    }

    for(i <- 0 to playfield.size - 1 ) {
      playfield = playfield.replaceCell(i, playfield.size - 1, Stop())
    }

    playfield = playfield.replaceCell(playerbase(0).x, playerbase(0).y, HeroCell())
  }


  def gameStart: Unit = {
    //var field = new Field(5)
    var x = false
    while (true) {
      for (a <- playerbase) {
        println("Spieler " + a.name + " ist wieder dran")
        var input = scala.io.StdIn.readLine()
        while(!goodmove(input)) {
            println("Spieler " + a.name + " ist dran")
            input = scala.io.StdIn.readLine()
          }
        prin
        }
      }
    }

  def move(row: Int, col: Int): Boolean = {
    playfield = playfield.replaceCell(playerbase(0).x, playerbase(0).y, Leer())
    playfield = playfield.replaceCell(row, col, HeroCell())
    playerbase(0).x = row
    playerbase(0).y = col
    true
  }

  def goodmove(dir: String): Boolean = {
    dir match {
      case "w" => checkmove(0)
      case "a" => checkmove(1)
      case "s" => checkmove(2)
      case "d" => checkmove(3)
      case _ => println("keine gültige eingabe. w = Oben, s = Unten, a = rechts, d = links"); false
    }
  }

  def checkmove(dir: Int): Boolean ={
    var cell = new Cell()
    var x = 0
    var y = 0
    dir match {
      case 0 =>  x = - 1
      case 1 => y = -1
      case 2 => x = 1
      case 3 => y = 1
      case _ => println("error")
    }
    cell = playfield.cell(playerbase(0).x + x , playerbase(0).y + y)
    cell match {
      case Stop() => println("nicht begehbar"); false
      case Leer()  => move(playerbase(0).x + x , playerbase(0).y + y)
      case _ => println("Kein gültiger Zug");false
    }
  }


}
