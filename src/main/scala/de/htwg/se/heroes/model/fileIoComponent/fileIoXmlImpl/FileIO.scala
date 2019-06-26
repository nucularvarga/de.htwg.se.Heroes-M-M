package de.htwg.se.heroes.model.fileIoComponent.fileIoXmlImpl

import de.htwg.se.heroes.model.fieldComponent.{ArenaInterface, FieldInterface}
import de.htwg.se.heroes.model.fileIoComponent.FileIOInterface
import de.htwg.se.heroes.model.playerComponent.PlayerListInterface

class FileIO extends FileIOInterface{

  override def load_Arena: ArenaInterface = ???

  override def load_Field: FieldInterface = ???

  override def load_PlayerList: PlayerListInterface = ???

  override def save_Arena(arena: ArenaInterface): Unit = ???

  override def save_Field(field: FieldInterface): Unit = ???

  override def save_PlayerList(playerList: PlayerListInterface): Unit = ???
}
