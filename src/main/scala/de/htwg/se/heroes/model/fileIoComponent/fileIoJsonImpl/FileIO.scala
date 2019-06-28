package de.htwg.se.heroes.model.fileIoComponent.fileIoJsonImpl



import com.google.inject.Guice
import de.htwg.se.heroes.HeroesModule
import de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl.{Cell, EnemyCell, HeroCell, Leer, Stop}
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.heroes.model.fieldComponent.{ArenaInterface, CellInterface, FieldInterface}
import de.htwg.se.heroes.model.fileIoComponent.FileIOInterface
import de.htwg.se.heroes.model.playerComponent.playerListBaseImpl.Player
import de.htwg.se.heroes.model.playerComponent.{PlayerInterface, PlayerListInterface}
import de.htwg.se.heroes.model.soldier.SoldierInterface
import de.htwg.se.heroes.model.soldier.soldierBaseImpl.Soldier
import play.api.libs.json._

import scala.collection.immutable.ListMap
import scala.io.Source
import scala.xml.{NodeSeq, PrettyPrinter}

class FileIO extends FileIOInterface{

  //override def load_Arena: ArenaInterface = ???

  override def load_Field: FieldInterface = {
    var field: FieldInterface = null
    val sourceFile = Source.fromFile("field.json")
    val source: String = sourceFile.getLines.mkString
    sourceFile.close()
    val json: JsValue = Json.parse(source)
    val size = (json \ "field" \ "x").get.toString.toInt
    val injector = Guice.createInjector(new HeroesModule)
    field = injector.instance[FieldInterface]

    for (index <- 0 until size * size) {
      val row = (json \\ "row")(index).as[Int]
      val col = (json \\ "col")(index).as[Int]
      val cell = (json \\ "cell")(index)
      val value = (cell \ "value").as[String]
      println(value)
      val typ = value match  {
        case " " => Leer()
        case "X" => Stop()
        case "F" => EnemyCell(5)
        case _ => HeroCell(value)
      }
      field = field.set(col, row, typ)
    }
    field
  }

  override def load_PlayerList: PlayerListInterface = {
    var playlist: PlayerListInterface = null
    val sourceFile = Source.fromFile("playlist.json")
    val source: String = sourceFile.getLines.mkString
    sourceFile.close()
    val json: JsValue = Json.parse(source)
    val size = (json \ "playerlist" \ "amount").get.toString.toInt
    val injector = Guice.createInjector(new HeroesModule)
    playlist = injector.instance[PlayerListInterface]

    for (index <- 0 until size ) {
      val name = (json \\ "name")(index).as[String]
      val gold = (json \\ "gold")(index).as[Int]
      val strength = (json \\ "strength")(index).as[Int]
      val x = (json \\ "x")(index).as[Int]
      val y = (json \\ "x")(index).as[Int]

      playlist = playlist.addPlayer(name, gold, strength, new ListMap[SoldierInterface, Int],x,y)
    }
    playlist
  }

  def save_Arena(arena: ArenaInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("arena.json"))
    pw.write(Json.prettyPrint(arenaToJson(arena)))
    pw.close()
  }

  override def save_Field(field: FieldInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("field.json"))
    pw.write(Json.prettyPrint(fieldToJson(field)))
    pw.close()
  }


  override def save_PlayerList(playerList: PlayerListInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("playlist.json"))
    pw.write(Json.prettyPrint(playlistToJson(playerList)))
    pw.close()
  }

  implicit val cellWrites = new Writes[CellInterface] {
    def writes(cell: CellInterface) = Json.obj(
      "value" -> cell.value,
    )
  }

  def arenaToJson(arena: ArenaInterface) = {
    Json.obj(
      "arena" -> Json.obj(
        "x" -> JsNumber(arena.size._1),
        "y" -> JsNumber(arena.size._2),
        "cells" -> Json.toJson(
          for {
            row <- 0 until arena.size._1
            col <- 0 until arena.size._2
          } yield {
            Json.obj(
              "row" -> row,
              "col" -> col,
              "cell" -> Json.toJson(arena.cell(row,col))
            )
          }
        )
      )
    )
  }

  def fieldToJson(field: FieldInterface) = {
    Json.obj(
      "field" -> Json.obj(
        "x" -> JsNumber(field.size),
        "cells" -> Json.toJson(
          for {
            row <- 0 until field.size
            col <- 0 until field.size
          } yield {
            Json.obj(
              "row" -> row,
              "col" -> col,
              "cell" -> Json.toJson(field.cell(col,row))
            )
          }
        )
      )
    )
  }

  implicit val PlayerWrites = new Writes[PlayerInterface] {
    def writes(player: PlayerInterface) = Json.obj(
      "name" -> player.asInstanceOf[Player].name,
      "gold" -> player.asInstanceOf[Player].gold,
      "strength" -> player.asInstanceOf[Player].strength,
      "x" -> player.asInstanceOf[Player].x,
      "y" -> player.asInstanceOf[Player].y,
    )
  }

  implicit val UnitWrites = new Writes[SoldierInterface] {
    def writes(soldier: SoldierInterface) = Json.obj(
      "typ" -> soldier.getTyp,
      "cost" -> soldier.getCost,
      "str" -> soldier.getStrength,
      //"range" -> soldier.getRange,
    )
  }


  //name: String, gold: Int, strength: Int, units: Map[Soldier, Int], x: Int, y: Int
  def playlistToJson(playlist: PlayerListInterface) = {
    var unitVector: Vector[SoldierInterface] = Vector.empty
    var amountVector: Vector[Int] = Vector.empty
    Json.obj(
      "playerlist" -> Json.obj(
        "turn" -> JsNumber(playlist.getTurn),
        "amount" -> JsNumber(playlist.getSize),
        "players" -> Json.toJson(
       for {
        player <- 0 until playlist.getSize
        } yield {
         unitVector = Vector.empty
         amountVector = Vector.empty
         for (e <- playlist.getPlayer(player).units) unitVector = unitVector :+ e._1
         for (e <- playlist.getPlayer(player).units) amountVector = amountVector :+ e._2
          Json.obj(
            "player" -> Json.toJson(playlist.getPlayer(player)),
            "units" -> Json.toJson(
              for {
                unit <- unitVector.indices
              } yield {
                Json.obj(
                  "unit" -> Json.toJson(UnitWrites.writes(unitVector(unit))),
                  "amount" -> JsNumber(amountVector(unit)),
                )
              }
            )
          )
        }
        )
      )
    )
    }

}
