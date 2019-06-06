package de.htwg.se.heroes.aview

class GUI_Thread extends Runnable {

  def run: Unit = {
    val ar: Array[String] = Array.empty
    GUI.main(ar)
  }

}
