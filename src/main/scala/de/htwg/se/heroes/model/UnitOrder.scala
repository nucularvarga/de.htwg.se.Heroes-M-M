package de.htwg.se.heroes.model

case class UnitOrder(units: Map[Soldier, Int]) {

  def addUnit(unit: Soldier, amount: Int, costs: Int): UnitOrder = {
    if(units.contains(unit)) {
      val tmp = units(unit) + amount
      copy(units.updated(unit, tmp))
    } else {
      copy(units.updated(unit, amount))
    }
  }

  def remove(): UnitOrder = {
    this
  }

  def boostMoral(): UnitOrder = {
    this
  }

  def createIterator: UnitOrderIterator = {
    UnitOrderIterator(this)
  }

}
