package de.htwg.se.heroes.controller

import de.htwg.se.heroes.model._
import de.htwg.se.heroes.util.{Observable, UndoManager}

import scala.collection.immutable.ListMap
import Event._

class Controller(var playField:Field, var playArena:Arena) extends Observable {

  var playerBase = new PlayerList
  val messanger = new Messanger
  var mode: GameMode = MapMode(playField, playerBase)
  private val undoManager = new UndoManager

  def createNewField(size: Int): Unit = {
    playField = new Field(size)
    notifyObservers
  }

  def init(): Unit = {
    playerBase = playerBase.addPlayer("1", 100, 100, new ListMap[Soldier, Int],  6, 6)
    playerBase = playerBase.addPlayer("2", 100, 100,  new ListMap[Soldier, Int], 3, 3)

    playField = playField.initField
    playField = playField.set(6, 6, HeroCell("1"))
    playField = playField.set(3, 3, HeroCell("2"))

    playField = playField.set(3, 7, EnemyCell(2))
    mode = MapMode(playField, playerBase)
    notifyObservers
  }

  def action(d : Event): Unit = {
    d match {
      case MoveUp => handle(Event.MoveUp)
      case MoveDown => handle(Event.MoveDown)
      case MoveRight => handle(Event.MoveRight)
      case MoveLeft => handle(Event.MoveLeft)
    }
    notifyObservers
  }

  def handle(e: Event) = undoManager.doStep(new MapCommand(mode.asInstanceOf[MapMode], e, this))

  def showStats(): Unit = {
    messanger.setMsg(playerBase.getPlayer.toString)
    notifyObservers
  }

  def openShop(number: Int): Unit = {
    if(playerBase.getPlayer.gold > number * Soldier(0,0).cost) {
      playerBase = playerBase.setUnits(number, number * Soldier(0,0).cost)
      messanger.setMsg("Erfolgreich gekauft")
    } else messanger.setMsg("Nicht genug gold")
    notifyObservers
  }

  def undo: Unit = {
    undoManager.undoStep
    notifyObservers
  }

  def playgroundToString: String = {
      mode.toString + messanger.getMsg

  }
}
