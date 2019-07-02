package de.htwg.se.heroes.model.zoomComponent.zoomBaseImpl

import de.htwg.se.heroes.controllerComponent.controllerBaseImpl.gamemode.UIEvent
import de.htwg.se.heroes.controllerComponent.controllerBaseImpl.gamemode.UIEvent.UIEvent
import de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl.Matrix

case class Zoom(x: Int, y: Int, view: Matrix) {

  val size = 9

  def updateMatrix(n_matrix: Matrix): Zoom = copy(x,y, n_matrix)

  def getMatrix: Matrix = view

  def show(event: UIEvent): Zoom = {
    val off = offset(event)
    var pos_x = 4
    var pos_y = 4
    var matrix = new Matrix(9)
    /*look if view fits on screen */
    if(x + off._1 > 3) {
      pos_x = x + off._1
    }
    if(x + off._1 > 15) {
      pos_x = 15
    }

    if(y + off._2 > 3) {
      pos_y = y + off._2
    }
    if(y + off._2 > 15) {
      pos_y = 15
    }

    for{
      row <- pos_y until size + pos_y
      col <- pos_x until size + pos_x
    } matrix = matrix.replaceCell(col - pos_x , row - pos_y, view.cell(col - 4 , row - 4))

    copy(pos_x, pos_y, matrix)
  }

  def offset(e: UIEvent): (Int, Int) = {
    e match{
    case UIEvent.MoveUp => (0, -1)
    case UIEvent.MoveDown => (0, 1)
    case UIEvent.MoveLeft => (-1, 0)
    case UIEvent.MoveRight => (1, 0)
    case _ => (0, 0)
    }
  }

}
