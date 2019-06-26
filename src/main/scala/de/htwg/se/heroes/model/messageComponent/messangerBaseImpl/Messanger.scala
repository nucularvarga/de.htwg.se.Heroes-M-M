package de.htwg.se.heroes.model.messageComponent.messangerBaseImpl

import de.htwg.se.heroes.model.messageComponent.MessangerInterface

class Messanger() extends MessangerInterface {

  var msg = ""
  var msgContainer = Vector.empty[String]

  def getMsg: String = {
    if(msgContainer.isEmpty)
      ""
    else {
      val a = msgContainer.last
      msgContainer = msgContainer.tail
      a
    }
  }

  def setMsg(save: String): Unit = {
    msgContainer = msgContainer :+ save
    msgContainer = msgContainer :+ save
  }

}
