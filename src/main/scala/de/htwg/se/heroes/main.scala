package de.htwg.se.heroes

object entry{
  def main(args: Array[String]): Unit = {
    val test = new Feld(10)
    Worldbuilder.init(10, test)
    Worldbuilder.line(3,2,1,0,test)

    
    print (test.printf)
  }
}