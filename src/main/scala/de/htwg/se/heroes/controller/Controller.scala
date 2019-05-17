package de.htwg.se.heroes.controller

import de.htwg.se.heroes.model._
import de.htwg.se.heroes.util.Observable

import scala.collection.immutable.ListMap
import Event._

class Controller(var playField:Field, var playArena:Arena) extends Observable {

  var playerBase = new PlayerList
  val messanger = new Messanger
  var mode: GameMode = MapMode(playField, playerBase)

  def createNewField(size: Int): Unit = {
    playField = new Field(size)
    notifyObservers
  }

  def init(): Unit = {
    playerBase = playerBase.addPlayer("1", 100, 100, UnitOrder(new ListMap[Soldier, Int]),  6, 6)
    playerBase = playerBase.addPlayer("2", 100, 100, UnitOrder(new ListMap[Soldier, Int]), 3, 3)

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
  //  }
  }

  def handle(e: Event) = mode = mode.handle(e)

/*
  def setSoldier(enemy: EnemyCell): Arena = {
    var unitVector: Vector[Cell] = Vector.empty
    for(e <- playerBase.getPlayer.units) unitVector = unitVector :+ e._1
    for {row <- 0 until playerBase.getPlayer.units.size} {
      playArena = playArena.set(row + 1, 2, unitVector(row))
      playArena = playArena.set(row + 1, 27, enemy)
    }


    playArena
  }
*/
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



  def playgroundToString: String = {
      mode.toString + messanger.getMsg

  }
}
