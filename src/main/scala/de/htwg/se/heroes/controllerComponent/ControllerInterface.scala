package de.htwg.se.heroes.controllerComponent

import de.htwg.se.heroes.controllerComponent.controllerBaseImpl.gamemode.GameMode
import de.htwg.se.heroes.controllerComponent.controllerBaseImpl.gamemode.UIEvent.UIEvent
import de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl.Cell
import play.api.libs.json.JsObject

import scala.swing.Publisher


trait ControllerInterface extends Publisher{
  def init(number: String): Unit
  def createNewField(size: Int): Unit
  def action(d : UIEvent): JsObject
  def handle(e: UIEvent): Unit
  def showStats(): Unit
  def openShop(e: UIEvent, number: Int): Unit
  def save: Unit
  def load: Unit
  def undo: Unit
  def redo: Unit
  def playgroundToString: String
  def getMessage: String
  def getCell(x: Int, y:Int): Cell
  def show(e: UIEvent): JsObject
  def viewToString: String
  def getMatrixCell(x:Int, y: Int): Cell
  def selectEnemy(x: Int, y: Int): Unit
  def getMode: GameMode
  def getJson: JsObject
}


import scala.swing.event.Event

class FieldChanged extends Event
class GameStart extends Event
class ViewChanged extends Event
class Win extends Event