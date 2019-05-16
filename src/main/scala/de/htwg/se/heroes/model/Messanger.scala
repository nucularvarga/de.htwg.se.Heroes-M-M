package de.htwg.se.heroes.model


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
