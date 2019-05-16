package de.htwg.se.heroes.model

import Console.{GREEN, RED, RESET, YELLOW_B, UNDERLINED}

case class HeroCell(override val typ: String) extends Cell {
  //override def toString: String = s"$RESET$RED" + typ + s"$RESET"
  override def toString: String = typ
}