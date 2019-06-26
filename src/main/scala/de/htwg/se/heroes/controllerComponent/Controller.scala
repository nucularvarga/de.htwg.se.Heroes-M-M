package de.htwg.se.heroes.controllerComponent


import de.htwg.se.heroes.util.UndoManager

import scala.collection.immutable.ListMap
import UIEvent._
import com.google.inject.name.Names
import com.google.inject.{Guice, Inject}
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.heroes.HeroesModule
import de.htwg.se.heroes.model.fieldComponent.{ArenaInterface, Cell, EnemyCell, Field, FieldInterface, HeroCell}
import de.htwg.se.heroes.model.playerComponent.{Player, PlayerList, PlayerListInterface, Soldier}
import de.htwg.se.heroes.model.messageComponent.{Messanger, MessangerInterface}

import scala.swing.Publisher
import scala.swing.event.Event



class Controller @Inject()(var playField:FieldInterface, var playArena:ArenaInterface) extends ControllerInterface with Publisher {

  val injector = Guice.createInjector(new HeroesModule)
  var playerBase = injector.instance[PlayerListInterface]//PlayerList(Vector.empty[Player], 0)
  val messanger = new Messanger
  var mode: GameMode = MapMode(playField, playerBase)
  var saveMap = mode
  val undoManager = new UndoManager


  def createNewField(size: Int): Unit = {
    playField = injector.instance[FieldInterface]
    publish(new FieldChanged)
  }

  def init(): Unit = {
    playerBase = playerBase.addPlayer("1", 100, 100, new ListMap[Soldier, Int],  6, 6)
    playerBase = playerBase.addPlayer("2", 100, 100,  new ListMap[Soldier, Int], 3, 3)
    playField = playField.initField
    playField = playField.set(6, 6, HeroCell("1"))
    playField = playField.set(3, 3, HeroCell("2"))

    playField = playField.set(3, 7, EnemyCell(2))
    mode = MapMode(playField, playerBase)
    publish(new FieldChanged)
  }

  def action(d : UIEvent): Unit = {
    d match {
      case MoveUp => handle(UIEvent.MoveUp)
      case MoveDown => handle(UIEvent.MoveDown)
      case MoveRight => handle(UIEvent.MoveRight)
      case MoveLeft => handle(UIEvent.MoveLeft)
    }
    publish(new FieldChanged)
  }

  def handle(e: UIEvent): Unit = {
    mode match {
      case f: MapMode => saveMap = mode; undoManager.doStep(new MapCommand(mode.asInstanceOf[MapMode], e, this))
      case f: CombatMode => undoManager.doStep(new CombatCommand(mode.asInstanceOf[CombatMode], e, this))
      case _ =>
    }
  }

  def showStats(): Unit = {
    messanger.setMsg(mode.asInstanceOf[MapMode].playerBase.getPlayer.toString)
    publish(new FieldChanged)
  }

  def openShop(number: Int): Unit = {
    if(mode.playlist.getPlayer.gold > number * Soldier(0,0).cost) {
      mode = mode.updatePlayerBase(mode.playlist.setUnits(number, number * Soldier(0,0).cost))
      messanger.setMsg("Erfolgreich gekauft")
    } else messanger.setMsg("Nicht genug gold")
    publish(new FieldChanged)
  }

  def undo: Unit = {
    undoManager.undoStep
    publish(new FieldChanged)
  }

  def redo: Unit = {
    undoManager.redoStep
    publish(new FieldChanged)
  }

  def playgroundToString: String = {
      mode.toString + messanger.getMsg
  }

  def getMessage: String = {
    messanger.getMsg
  }

  def getCell(x: Int, y: Int): Cell = {
    mode.cell(x,y)
  }

}
