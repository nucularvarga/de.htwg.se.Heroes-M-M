package de.htwg.se.heroes.model

case class Player(name: String, gold: Int, strength: Int, x: Int, y: Int) {


  def walk(nx: Int, ny: Int): Player = this.copy(name, gold, strength, nx, ny)

  def powerUp(str: Int): Player = copy(this.name, this.gold, str, this.x, this.y)
}
