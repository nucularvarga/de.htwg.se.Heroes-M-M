package de.htwg.se.heroes

import com.google.inject.AbstractModule
import de.htwg.se.heroes.controllerComponent.{Controller, ControllerInterface}
import de.htwg.se.heroes.model.fieldComponent._
import de.htwg.se.heroes.model.fileIoComponent._
import de.htwg.se.heroes.model.playerComponent.{Player, PlayerList, PlayerListInterface}
import net.codingwell.scalaguice.ScalaModule

class HeroesModule extends AbstractModule with ScalaModule {

  def configure() = {

    bind[ControllerInterface].to[Controller]
    bind[FieldInterface].toInstance(new Field(9))
    bind[ArenaInterface].toInstance(new Arena(9,9))
    bind[PlayerListInterface].toInstance(PlayerList(Vector.empty[Player], 0))
    bind[FieldInterface].annotatedWithName("field").toInstance(Field(Matrix(Vector.tabulate(9, 9) { (y, x) => Leer() })))
    bind[ArenaInterface].annotatedWithName("arena").toInstance(Arena(Matrix(Vector.tabulate(9, 9) { (y, x) => Leer() })))

    bind[FileIOInterface].to[fileIoJsonImpl.FileIO]
  }
}
