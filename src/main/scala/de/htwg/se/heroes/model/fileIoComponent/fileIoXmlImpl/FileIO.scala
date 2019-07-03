package de.htwg.se.heroes.model.fileIoComponent.fileIoXmlImpl

import com.google.inject.Guice
import de.htwg.se.heroes.HeroesModule
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl.{EnemyCell, HeroCell, Leer, Stop}
import de.htwg.se.heroes.model.fieldComponent.{ArenaInterface, FieldInterface}
import de.htwg.se.heroes.model.fileIoComponent.FileIOInterface
import de.htwg.se.heroes.model.playerComponent.PlayerListInterface
import de.htwg.se.heroes.model.playerComponent.playerListBaseImpl.Player
import de.htwg.se.heroes.model.soldier.SoldierInterface
import de.htwg.se.heroes.model.soldier.soldierBaseImpl.Soldier

import scala.collection.immutable.ListMap
import scala.io.Source
import scala.xml.PrettyPrinter

class FileIO extends FileIOInterface{

  //override def load_Arena: ArenaInterface = ???

  override def load_Field: FieldInterface = {
    var field: FieldInterface = null
    val file = scala.xml.XML.loadFile("field.xml")
    val sizeAttr = (file \\ "field" \ "@size")
    val size = sizeAttr.text.toInt
    val injector = Guice.createInjector(new HeroesModule)
    field = injector.instance[FieldInterface]

    val cellNodes = (file \\ "cell")
    for (cell <- cellNodes) {
      val row: Int = (cell \ "@row").text.toInt
      val col: Int = (cell \ "@col").text.toInt
      val value = (cell \ "@typ").text.toString
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
    val file = scala.xml.XML.loadFile("playlist.xml")

    val size = (file \\ "playerList" \ "@amount")
    val amount = size.text.toInt
    val injector = Guice.createInjector(new HeroesModule)
    playlist = injector.instance[PlayerListInterface]

    val playerNodes = (file \\ "playerinfo" \\ "player")
    for (player <- playerNodes ) {
      val name: String = (player \ "@name").text.toString
      val gold: Int = (player \ "@gold").text.toInt
      val strength: Int = (player \ "@strength").text.toInt
      val x: Int = (player \ "@x").text.toInt
      val y: Int = (player \ "@y").text.toInt

      playlist = playlist.addPlayer(name, gold, strength, new ListMap[Soldier, Int],x,y)
    }
    playlist
  }


  //override def save_Arena(arena: ArenaInterface): Unit = ???

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
    <cell row={ row.toString } col={ col.toString } typ={ grid.cell(col, row).toString }>
    </cell>
  }

  def playerListToXml(playlist: PlayerListInterface) = {
    <playerList turn={ playlist.getTurn.toString } amount ={ playlist.getSize.toString} >
      {
      var unitVector: Vector[SoldierInterface] = Vector.empty
      var amountVector: Vector[Int] = Vector.empty
      <playerinfo>
        {
        for {
          player <- 0 until playlist.getSize
        } yield {
          <player name={playlist.getPlayer(player).name} gold={playlist.getPlayer(player).gold.toString}
                  strength={playlist.getPlayer(player).strength.toString} x={playlist.getPlayer(player).x.toString}
                  y={playlist.getPlayer(player).y.toString}>
          </player>
        }
        }
      </playerinfo>
        <Units>
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
            <unit unit={unitVector(unit).toString} amount={amountVector(unit).toString} cost={unitVector(unit).getCost.toString} strength={unitVector(unit).getStrength.toString}></unit>
          }
        }}
        </Units>
      }
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
