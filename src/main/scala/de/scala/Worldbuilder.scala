package de.scala

object Worldbuilder {

  def line(size: Int, row: Int, col: Int, direction: Int, world: Feld): Feld = {
      if(direction==0) {
        for(n <- col.to(col+size)) world.replaceCell(row, n, new Tile(4))
        world
      } else {
        for(n <- row.to(row+size)) world.replaceCell(n, row, new Tile(4))
        world
      }
  }

  def init(size: Int, world: Feld): Feld = {
    for (n <- 0.to(9)) {
      for (m <- 0.to(9)) world.replaceCell(n, m, new Tile(0))
    }
    world
  }
}
