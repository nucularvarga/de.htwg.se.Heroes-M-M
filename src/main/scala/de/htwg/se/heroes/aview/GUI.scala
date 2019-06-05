package de.htwg.se.heroes.aview

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.{Node, Scene}
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle
import de.htwg.se.heroes.controller.{Controller, Event}
import de.htwg.se.heroes.main.controller
import de.htwg.se.heroes.util.Observer
import scalafx.application
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.control.Button
import de.htwg.se.heroes.model._
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.{BorderPane, GridPane}



//class CellClicked(val row: Int, val column: Int) extends Event



object GUI extends JFXApp {
  val controller = new Controller(new Field(9), new Arena(8, 20))
  controller.init()
  stage = new JFXApp.PrimaryStage {
    outer =>
    title = "Hello Stage"
    //fullScreen_=(true)
    scene = new Scene(880, 680) {
        val map = new GridPane {
          padding = Insets(5)
          vgap = 0
          hgap = 0
          val buttom = new Button {
            text = "Click me to close the dialog"
            onAction = handle {
              outer.close()
            }
          }

          for {
            y <- 0 until 9
            x <- 0 until 9
          } add(drawTexture(controller.playField.cell(x,y)), x, y)
        }



        val buton = new GridPane {
          val exit = new Button {
            text = "Click me to close the dialog"
            onAction = handle {
              outer.close()
            }
          }
            val up = new Button {
              text = "Up"
              onAction = handle {
                controller.action(Event.MoveUp)
                drawMap
              }
            }

          add(exit, 0, 0)
          add(up, 0, 1)
        }
      def drawMap = {
        print("print test")
        for {
          y <- 0 until 9
          x <- 0 until 9
        } map.add(drawTexture(controller.mode.cell(x, y)), x, y)
        print(controller.playgroundToString)
      }
      buton.layoutX = 640
      buton.layoutY = 100
      content = List(map, buton)
    }
  }



  def drawTexture(cell: Cell): Node = {
    val typ = cell match {
      case f: HeroCell => new Image("file:hero.png")
      case Leer() => new Image("file:grass.jpg")
      case Stop() => new Image("file:berg.jpg")
      case f: EnemyCell => new Image("file:drake.jpg")
    }
    new ImageView(typ)
  }
}