package de.htwg.se.heroes.model.fileIoComponent.fileIoJsonImpl

import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.heroes.model.fieldComponent.{ArenaInterface, CellInterface, FieldInterface}
import de.htwg.se.heroes.model.fileIoComponent.FileIOInterface
import de.htwg.se.heroes.model.playerComponent.PlayerListInterface
import play.api.libs.json._

import scala.xml.{NodeSeq, PrettyPrinter}

class FileIO extends FileIOInterface{

  override def load_Arena: ArenaInterface = ???

  override def load_Field: FieldInterface = ???

  override def load_PlayerList: PlayerListInterface = ???

  override def save_Arena(arena: ArenaInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("grid.json"))
    pw.write(Json.prettyPrint(arenaToJson(arena)))
    pw.close()
  }

  override def save_Field(field: FieldInterface): Unit = ???

  override def save_PlayerList(playerList: PlayerListInterface): Unit = ???

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


}
