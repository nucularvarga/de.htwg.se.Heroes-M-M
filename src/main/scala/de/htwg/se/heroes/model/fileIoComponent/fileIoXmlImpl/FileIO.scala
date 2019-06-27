package de.htwg.se.heroes.model.fileIoComponent.fileIoXmlImpl

import de.htwg.se.heroes.model.fieldComponent.{ArenaInterface, FieldInterface}
import de.htwg.se.heroes.model.fileIoComponent.FileIOInterface
import de.htwg.se.heroes.model.playerComponent.PlayerListInterface
import de.htwg.se.heroes.model.playerComponent.playerListBaseImpl.Player
import de.htwg.se.heroes.model.soldier.soldierBaseImpl.Soldier

import scala.xml.PrettyPrinter

class FileIO extends FileIOInterface{

  override def load_Arena: ArenaInterface = ???

  override def load_Field: FieldInterface = ???

  override def load_PlayerList: PlayerListInterface = ???

  override def save_Arena(arena: ArenaInterface): Unit = ???

  override def save_Field(field: FieldInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("field.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(fieldToXml(field))
    pw.write(xml)
    pw.close()
  }

  def fieldToXml(field: FieldInterface) = {
    <field size={ field.size.toString }>
      {
      for {
        row <- 0 until field.size
        col <- 0 until field.size
      } yield cellToXml(field, row, col)
      }
    </field>
  }

  def cellToXml(grid: FieldInterface, row: Int, col: Int) = {
    <cell row={ row.toString } col={ col.toString } typ={ grid.cell(row, col).toString }>
    </cell>
  }

  def playerListToXml(playlist: PlayerListInterface) = {
    <playerList turn = {playlist.getTurn.toString}>
      {var unitVector: Vector[Soldier] = Vector.empty
    var amountVector: Vector[Int] = Vector.empty
    <playerinfo>
      {for {
      player <- 0 until playlist.getSize
    } yield {
      <player name={playlist.getPlayer(player).name} gold={playlist.getPlayer(player).gold.toString}
              strength={playlist.getPlayer(player).strength.toString} x={playlist.getPlayer(player).x.toString}
              y={playlist.getPlayer(player).y.toString}>
      </player>
    }}
    </playerinfo>
      <asd>
        {for {
        player <- 0 until playlist.getSize
      } yield {
        unitVector = Vector.empty
        amountVector = Vector.empty
        for (e <- playlist.getPlayer(player).units) unitVector = unitVector :+ e._1
        for (e <- playlist.getPlayer(player).units) amountVector = amountVector :+ e._2

        for {
          unit <- unitVector.indices
        } yield {
          <unit unit={unitVector(unit).toString} amount={amountVector(unit).toString}></unit>
        }
      }}
      </asd>}
    </playerList>
  }


  override def save_PlayerList(playerList: PlayerListInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("playlist.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(playerListToXml(playerList))
    pw.write(xml)
    pw.close()
  }
}
