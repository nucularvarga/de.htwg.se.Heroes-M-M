package de.scala

object entry{
  def main(args: Array[String]): Unit = {
    val field = Array.ofDim[Tile](10,10)
    Worldbuilder.init(10, field)
    field(1)(1) = new Tile(3)
    Worldbuilder.line(3,2,1,0,field)
    val test = new Feld(field)
    print (test.printf)
  }
}