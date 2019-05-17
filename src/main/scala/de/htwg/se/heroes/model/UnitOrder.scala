package de.htwg.se.heroes.model

case class UnitOrder(units: Map[Soldier, Int]) {

  val size = units.size

  def addUnit(unit: Soldier, amount: Int): UnitOrder = {
    if(units.contains(unit)) {
      val tmp = units(unit) + amount
      copy(units.updated(unit, tmp))
    } else {
      copy(units.updated(unit, amount))
    }
  }

  def move(unit: Soldier, amount: Int, x: Int, y: Int): UnitOrder = {
    var f = remove(unit)
    f = f + (unit.moveUnit(x,y) -> amount)
    copy(f)
  }

  def remove(unit: Soldier): Map[Soldier, Int] = {
    units - unit
  }

  def boostMoral(): UnitOrder = {
    this
  }

  def createIterator: UnitOrderIterator = {
    UnitOrderIterator(this)
  }

}
