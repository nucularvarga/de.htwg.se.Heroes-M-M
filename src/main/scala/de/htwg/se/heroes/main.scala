package de.htwg.se.heroes.model

object entry{
  def main(args: Array[String]): Unit = {
    val p = new Playground(10)
    p.playfield = p.init
    p.gameStart

  }
}