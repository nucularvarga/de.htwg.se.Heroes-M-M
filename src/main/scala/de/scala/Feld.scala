package de.scala

class Feld(val stats: Array[Array[Tile]]) {
  def printf:Unit = {
    for (n <- 0.to(9)) {
      for (m <- 0.to(9)) print(stats(n)(m))
      println()
    }
  }
}
