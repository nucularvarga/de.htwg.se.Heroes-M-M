package de.scala

object Worldbuilder {

  def line(size: Int, row: Int, col: Int, direction: Int, world: Array[Array[Tile]]): Array[Array[Tile]] = {
      val newWorld = world
      if(direction==0) {
        for(n <- col.to(col+size)) newWorld(row)(n) = new Tile(4)
        newWorld
      } else {
        for(n <- row.to(row+size)) newWorld(n)(col) = new Tile(4)
        newWorld
      }
  }

  def init(size: Int, world: Array[Array[Tile]]): Array[Array[Tile]] = {
    for (n <- 0.to(9)) {
      for (m <- 0.to(9)) world(n)(m) = new Tile(0)
    }
    world
  }
}
