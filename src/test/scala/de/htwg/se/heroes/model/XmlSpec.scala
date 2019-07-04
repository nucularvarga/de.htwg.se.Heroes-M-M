package de.htwg.se.heroes.model

import com.google.inject.Guice
import de.htwg.se.heroes.HeroesModule
import de.htwg.se.heroes.model.fieldComponent.FieldInterface
import de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl.{Field, Matrix}
import de.htwg.se.heroes.model.fileIoComponent.fileIoXmlImpl.FileIO
import de.htwg.se.heroes.model.playerComponent.PlayerListInterface
import de.htwg.se.heroes.model.playerComponent.playerListBaseImpl.PlayerList
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.heroes.model.soldier.soldierBaseImpl.Soldier
import org.scalatest.{Matchers, WordSpec}

import scala.collection.immutable.ListMap

class XmlSpec extends WordSpec with Matchers {
  "A XML impl" when {
    "a init file " should {
      var file = new FileIO
      var field = Field(new Matrix(2))
      "save a cell" in {
        file.cellToXml(field, 0, 0) should be(<cell row="0" col="0" typ=" "></cell>)
      }
      "save a field" in {
        file.fieldToXml(field) should be(<field size="2">
      <cell row="0" col="0" typ=" "></cell><cell row="0" col="1" typ=" "></cell><cell row="1" col="0" typ=" "></cell><cell row="1" col="1" typ=" "></cell>
    </field>)
      }
      "save a playerlist" in {
        val injector = Guice.createInjector(new HeroesModule)
        var playerBase = injector.instance[PlayerListInterface]
        playerBase = playerBase.addPlayer("1", 100, 100, new ListMap[Soldier, Int],  6, 6)
        playerBase = playerBase.addPlayer("2", 100, 100,  new ListMap[Soldier, Int], 8, 8)

        file.playerListToXml(playerBase) should be
        <playerList turn="0" amount="2">
          <playerinfo>
            <player name="1" gold="100" strength="100" x="6" y="6">
            </player><player name="2" gold="100" strength="100" x="8" y="8">
          </player>
          </playerinfo><Units>

          </Units>
        </playerList>
      }
      "load field" in {
        val injector = Guice.createInjector(new HeroesModule)
       // file.load_Field.toString should be(injector.instance[FieldInterface].toString)
      }
    }
  }
}
