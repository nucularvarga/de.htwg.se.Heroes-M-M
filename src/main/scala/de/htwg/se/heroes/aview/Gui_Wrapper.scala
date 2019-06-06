package de.htwg.se.heroes.aview

import de.htwg.se.heroes.controller.Controller
import de.htwg.se.heroes.util.Observer
import scalafx.stage.{Screen, Stage}

class Gui_Wrapper(controller: Controller) extends Observer {

  controller.add(this)

  def show(): Unit = {
    val ar: Array[String] = Array.empty
    GUI.main(ar)
  }



  override def update: Unit =  println("dummy message")
}
