package de.htwg.se.heroes.controllerComponent.controllerBaseImpl

import com.google.inject.{Guice, Inject}
import de.htwg.se.heroes.HeroesModule
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.heroes.controllerComponent.controllerBaseImpl.gamemode._
import de.htwg.se.heroes.controllerComponent.controllerBaseImpl.gamemode.UIEvent.UIEvent
import de.htwg.se.heroes.controllerComponent.controllerBaseImpl.gamemode.UIEvent._
import de.htwg.se.heroes.controllerComponent.{ControllerInterface, FieldChanged, ViewChanged}
import de.htwg.se.heroes.model.fieldComponent._
import de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl._
import de.htwg.se.heroes.model.fileIoComponent.FileIOInterface
import de.htwg.se.heroes.model.messageComponent.messangerBaseImpl.Messanger
import de.htwg.se.heroes.model.playerComponent.PlayerListInterface
import de.htwg.se.heroes.model.playerComponent.playerListBaseImpl.{Player, PlayerList}
import de.htwg.se.heroes.model.soldier.SoldierInterface
import de.htwg.se.heroes.model.soldier.soldierBaseImpl.{MeleeSoldier, RangeSoldier, Soldier}
import de.htwg.se.heroes.model.zoomComponent.zoomBaseImpl.Zoom
import de.htwg.se.heroes.util.UndoManager

import scala.collection.immutable.ListMap
import scala.swing.Publisher



class Controller @Inject()(var playField:FieldInterface, var playArena:ArenaInterface) extends ControllerInterface with Publisher {

  val injector = Guice.createInjector(new HeroesModule)
  val fileIo = injector.instance[FileIOInterface]
  var playerBase = injector.instance[PlayerListInterface]//PlayerList(Vector.empty[Player], 0)
  val messanger = new Messanger
  var mode: GameMode = MapMode(playField, playerBase)
  var saveMap = mode
  val undoManager = new UndoManager
  var matrix = new Matrix(9)
  var zoom = Zoom(8,8, new Matrix(20))



  def createNewField(size: Int): Unit = {
    playField = injector.instance[FieldInterface]
    publish(new FieldChanged)
  }

  def load(): Unit = { //TODO: GAMESTATUS ARENA/FIELD DAMIT WIR WISSEN WAS WIR LADEN MUESSEN!!!!!!!!!111!11!!1?
    mode  = MapMode(fileIo.load_Field, playerBase)
    //playArena = fileIo.load_Arena
    mode.updatePlayerBase(fileIo.load_PlayerList)
    publish(new FieldChanged)
  }

  def save(): Unit = {
    //fileIo.save_Arena(mode.playArena)
    fileIo.save_Field(saveMap.asInstanceOf[MapMode].playField)
    fileIo.save_PlayerList(mode.playlist)
    publish(new FieldChanged)
  }

  def init(number: String): Unit = {
    playerBase = playerBase.addPlayer("1", 100, 100, new ListMap[Soldier, Int],  6, 6)
    playField = playField.initField
    playField = playField.set(6, 6, HeroCell("1"))

    if (number.toInt == 2) {
      playerBase = playerBase.addPlayer("2", 100, 100, new ListMap[Soldier, Int], 8, 8)
      playField = playField.set(8, 8, HeroCell("2"))
      playField = playField.set(11, 9, GoldCell())
      playField = playField.set(16, 16, GoldCell())
      playField = playField.set(17, 8, EnemyCell(100))
    }

    playField = playField.set(3, 7, EnemyCell(20))
    playField = playField.set(11, 3, EnemyCell(50))
    playField = playField.set(10, 14, EnemyCell(100))
    playField = playField.set(3, 2, GoldCell())
    playField = playField.set(15, 3, GoldCell())
    playField = playField.set(4, 15, GoldCell())
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

  def openShop(e: UIEvent, number: Int): Unit = {
    val typ = e match {
      case BuyMelee => new MeleeSoldier(1,2)
      case BuyRange => new RangeSoldier(1,1)
      case _ => new MeleeSoldier(1,2) //TODO Flaschentransport
    }

    if (mode.playlist.getPlayer.gold > number * typ.cost) {
      mode = mode.updatePlayerBase (mode.playlist.setUnits(typ, number, number * typ.cost) )
      messanger.setMsg ("Erfolgreich gekauft")
    } else messanger.setMsg ("Nicht genug gold")
    publish (new FieldChanged)
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

  def getMatrixCell(x:Int, y: Int): Cell = {
    matrix.cell(x,y)
  }

  def show(e: UIEvent): Unit = {
    zoom = zoom.updateMatrix(mode.asInstanceOf[MapMode].playField.getMatrix)
    zoom = zoom.show(e)
    matrix = zoom.getMatrix
    publish(new ViewChanged)
  }

  override def viewToString: String = {
    matrix.toString
  }

}
