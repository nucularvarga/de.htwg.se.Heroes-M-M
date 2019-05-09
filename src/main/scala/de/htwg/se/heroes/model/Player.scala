package de.htwg.se.heroes.model

case class Player(name: String, gold: Int, strength: Int, units: Map[Cell, Int], x: Int, y: Int) {

  override def toString: String = name + ": Gold: " + gold + " Strength: " + strength + " Units: " + units.toString()// TODO add units

  def walk(nx: Int, ny: Int): Player = copy(name, gold, strength, units, x + nx, y + ny)

  def addUnit(unit: Cell, amount: Int, costs: Int): Player = {
    if(units.contains(unit)) {
      val tmp = units(unit) + amount
      copy(name, gold - costs, strength, units.updated(unit, tmp), x, y)
    } else {
      copy(name, gold - costs, strength, units.updated(unit, amount), x, y)
    }
  }

  def powerUp(str: Int): Player = copy(name, gold, str + strength, units, x, y)
}
