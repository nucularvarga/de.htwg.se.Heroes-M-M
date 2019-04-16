package de.htwg.se.heroes.model

case class Player(name: String, gold: Int, strength: Int, x: Int, y: Int) {

  override def toString: String = name + ": Gold: " + gold + " Strength: " + strength

  def walk(nx: Int, ny: Int): Player = this.copy(name, gold, strength, nx, ny)

  def powerUp(str: Int): Player = copy(this.name, this.gold, str + strength, this.x, this.y)
}
