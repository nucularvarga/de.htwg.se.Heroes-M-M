package de.htwg.se.heroes.aview

import de.htwg.se.heroes.model._

class Tui {

  def processInputLine(input: String, p:Playground):Playground = {
    input match {
      case "q" => p
      case "n"=> new Playground(10)
      case _ => p.evalInput(input)

    }
  }
}