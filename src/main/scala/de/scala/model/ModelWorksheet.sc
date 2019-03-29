class Tile(val status: Int) {
  override def toString = status.toString
}

class Feld(val stats: Array[Array[Tile]]) {
  def printf:Unit = {
    for (n <- 0.to(9)) {
      for (m <- 0.to(9)) print(stats(n)(m))
      println()
    }
  }
}

def init(size: Int, world: Feld): Feld = {
  for (n <- 0.to(9)) {
    for (m <- 0.to(9)) world.stats(n)(m) = new Tile(0)
  }
  world
}

def line(size: Int, row: Int, col: Int, direction: Int, world: Feld): Feld = {
  val newWorld = world
  if(direction==0) {
    for(n <- col.to(col+size)) newWorld.stats(row)(n) = new Tile(1)
    newWorld
  } else {
    for(n <- row.to(row+size)) newWorld.stats(n)(col) = new Tile(1)
    newWorld
  }
}

val test = new Feld(Array.ofDim[Tile](10,10))
val gamefield = init(10, test)
val gamefield1 = line(9,0,0,0,gamefield)

gamefield.printf
println()
gamefield1.printf

