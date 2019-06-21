package de.htwg.se.heroes.controllerComponent

import de.htwg.se.heroes.controllerComponent.controllerBaseImpl.gamemode.UIEvent.UIEvent
import de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl.Cell

import scala.swing.Publisher


trait ControllerInterface extends Publisher{
  def init(): Unit
  def createNewField(size: Int): Unit
  def action(d : UIEvent): Unit
  def handle(e: UIEvent): Unit
  def showStats(): Unit
  def openShop(e: UIEvent, number: Int): Unit
  def undo: Unit
  def redo: Unit
  def playgroundToString: String
  def getMessage: String
  def getCell(x: Int, y:Int): Cell
}


import scala.swing.event.Event

class FieldChanged extends Event
class GameStart extends Event