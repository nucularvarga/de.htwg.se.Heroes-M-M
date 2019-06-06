package de.htwg.se.heroes.aview

import de.htwg.se.heroes.controller.Controller
import de.htwg.se.heroes.util.Observer

class Gui_Wrapper(controller: Controller) extends Observer {

  controller.add(this)
  val gui = GUI



  override def update: Unit =  println("dummy message")
}
