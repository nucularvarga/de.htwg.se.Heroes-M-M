package de.htwg.se.heroes.model.messageComponent

trait MessangerInterface {
  def getMsg: String
  def setMsg(save: String): Unit
}
