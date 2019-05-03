package de.htwg.se.heroes.model


import de.htwg.se.heroes.controller.Direction
import de.htwg.se.heroes.controller.Direction._

class Messanger() {

  var msg = ""
  var msgContainer = Vector.empty[String]

  def getMsg: String = {
    val tmp = msg
    msg = ""
    tmp
  }

  def setMsg(save: String): Unit = {
    msg = save
  }

}
