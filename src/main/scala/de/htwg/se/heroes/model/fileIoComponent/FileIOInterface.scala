package de.htwg.se.heroes.model.fileIoComponent

import de.htwg.se.heroes.model.fieldComponent.{ArenaInterface, FieldInterface}
import de.htwg.se.heroes.model.playerComponent.PlayerListInterface

trait FileIOInterface {

  def load_Arena: ArenaInterface
  def load_Field: FieldInterface
  def load_PlayerList: PlayerListInterface

  def save_Arena(arena: ArenaInterface): Unit
  def save_Field(field: FieldInterface): Unit
  def save_PlayerList(playerList: PlayerListInterface): Unit

}
