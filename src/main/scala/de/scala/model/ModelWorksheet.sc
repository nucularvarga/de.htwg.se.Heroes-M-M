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
    for (m <- 0.to(9)) world.stats(n)(m) = new Tile(1)
  }
  world
}

val test = new Feld(Array.ofDim[Tile](10,10))
val gamefield = init(10, test)


gamefield.printf

