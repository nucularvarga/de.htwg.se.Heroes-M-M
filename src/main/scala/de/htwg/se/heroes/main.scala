package de.htwg.se.heroes

import de.htwg.se.heroes.aview.Tui

import de.htwg.se.heroes.model._

import scala.io.StdIn.readLine

object entry{
  var p = new Playground(10)
  val tui = new Tui
  def main(args: Array[String]): Unit = {
    var input: String = ""

    p.playfield = p.init
    p.playerbase = p.setPlayer("Kevin", 50, 100, 1, 1)
    p.playfield = p.replaceField(1, 1, HeroCell("1"))
    p.playerbase = p.setPlayer("Janko", 50, 100, 3, 3)
    p.playfield = p.replaceField(3, 3, HeroCell("2"))

    println("Spieler " + p.playerbase(0).name + " ist dran")

    do {
      println(p.toString)
      input = readLine()
      p = tui.processInputLine(input, p)
    } while (input != "q")
  }
}