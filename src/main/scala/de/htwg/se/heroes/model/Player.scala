package de.htwg.se.heroes.model

case class Player(name: String, gold: Int, strength: Int, units: Int, x: Int, y: Int) {

  override def toString: String = name + ": Gold: " + gold + " Strength: " + strength + " Units: " + units

  def walk(nx: Int, ny: Int): Player = copy(name, gold, strength, units, x + nx, y + ny)

  def addUnit(unit: Int, costs: Int): Player = copy(name, gold - costs, strength, unit + units, x, y)

  def powerUp(str: Int): Player = copy(name, gold, str + strength, units, x, y)
}
