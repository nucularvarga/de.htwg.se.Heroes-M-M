package de.htwg.se.heroes.model

case class Player(name: String, gold: Int, strength: Int, x: Int, y: Int) {

  def walk(x: Int, y: Int): Player = copy(this.name, this.gold, this.strength, x, y)

  def powerUp(str: Int): Player = copy(this.name, this.gold, str, this.x, this.y)
}
