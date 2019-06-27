package de.htwg.se.heroes

import com.google.inject.AbstractModule
import de.htwg.se.heroes.controllerComponent.ControllerInterface
import de.htwg.se.heroes.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.heroes.model.fieldComponent.{fieldBaseImpl, _}
import de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl.{Arena, Field, Leer, Matrix}
import de.htwg.se.heroes.model.fileIoComponent._
import de.htwg.se.heroes.model.playerComponent.PlayerListInterface
import de.htwg.se.heroes.model.playerComponent.playerListBaseImpl.{Player, PlayerList}
import net.codingwell.scalaguice.ScalaModule

class HeroesModule extends AbstractModule with ScalaModule {

  def configure() = {

    bind[ControllerInterface].to[Controller]
    bind[FieldInterface].toInstance(new Field(9))
    bind[ArenaInterface].toInstance(new Arena(9,9))
    bind[PlayerListInterface].toInstance(PlayerList(Vector.empty[Player], 0))
    bind[FieldInterface].annotatedWithName("field").toInstance(Field(Matrix(Vector.tabulate(9, 9) { (y, x) => Leer() })))
    bind[ArenaInterface].annotatedWithName("arena").toInstance(Arena(fieldBaseImpl.Matrix(Vector.tabulate(9, 9) { (y, x) => Leer() })))

    bind[FileIOInterface].to[fileIoXmlImpl.FileIO]
  }
}
