package controller

import de.htwg.se.heroes.controller.Direction.Value
import de.htwg.se.heroes.controller.GameMode.Value


object Direction extends Enumeration {
  type Direction = Value
  val Up, Left, Down, Right = Value
}

object GameMode extends Enumeration {
  type GameMode = Value
  val Map, Combat = Value
}


class ControllerSpec {

}
