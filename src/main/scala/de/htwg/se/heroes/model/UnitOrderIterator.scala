package de.htwg.se.heroes.model

case class UnitOrderIterator(alertagg: UnitOrder) extends UnitIterator {

  var pos = 0
  val list = alertagg.units

  def next: Soldier = {
    println(pos)
    val l = list.toList(pos)._1
    pos += 1
    l
  }

  def hasNext: Boolean = {
    if(pos >= list.size) {
      pos = 0
      false
    }
    else
      true
  }

  def ptr: Soldier = {
    list.toList(pos)._1
  }

}
