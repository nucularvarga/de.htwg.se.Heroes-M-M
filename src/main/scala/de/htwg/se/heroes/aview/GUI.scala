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
import scalafx.scene.control.{Button, Label, TextArea, TextField}
import de.htwg.se.heroes.model._
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.{BorderPane, GridPane}



//class CellClicked(val row: Int, val column: Int) extends Event



object GUI extends JFXApp {
  val controller = new Controller(new Field(9), new Arena(8, 20))
  controller.init()
  stage = new JFXApp.PrimaryStage {
    outer =>
    title = "Heroes of Might and Magic"
    //fullScreen_=(true)
    scene = new Scene(1024, 590) {

      val textinfo = new Label {
      }
      textinfo.setPrefWidth(250)
      textinfo.setPrefHeight(50)
      textinfo.setText("Spieler 1: Ist im Besitz des heiligen Gral!")
      textinfo.layoutX = 650
      textinfo.layoutY = 500
        val map = new GridPane {
          padding = Insets(5)
          vgap = 0
          hgap = 0

          for {
            y <- 0 until 9
            x <- 0 until 9
          } add(drawTexture(controller.playField.cell(x,y)), x, y)
        }



        val buton = new GridPane {
          val buyfield = new TextField {
            text = "0"
          }

          val buybutton = new Button {
            text = "Kaufen"
            onAction = handle {
              controller.openShop(buyfield.text().toInt)
              textinfo.text = controller.messanger.getMsg
              drawMap
            }
          }

          val exit = new Button {
            text = "exit"
            onAction = handle {
              outer.close()
            }
          }

          val info = new Button {
            text = "info"
            onAction = handle {
              controller.showStats()
              textinfo.text = controller.messanger.getMsg
            }
          }
          val up = new Button {
              text = "Up"
              onAction = handle {
                controller.action(Event.MoveUp)
                drawMap
              }
            }

          val right = new Button {
            text = "Right"
            onAction = handle {
              controller.action(Event.MoveRight)
              drawMap
            }
          }
          val Left = new Button {
            text = "Left"
            onAction = handle {
              controller.action(Event.MoveLeft)
              drawMap
            }
          }

          val revert = new Button {
            text = "Undo"
            onAction = handle {
              controller.undo
              drawMap
            }
          }

          val Down = new Button {
            text = "Down"
            onAction = handle {
              controller.action(Event.MoveDown)
              drawMap
            }
          }
          add(exit, 1, 0)
          add(up, 1, 1)
          add(right, 2, 2)
          add(Left, 0, 2)
          add(Down, 1, 2)
          add(buyfield, 3,3)
          add(buybutton, 4,3)
          add(info, 5,3)
          add(revert, 4,4)
        }
      def drawMap = {
        for {
          y <- 0 until 9
          x <- 0 until 9
        } map.add(drawTexture(controller.mode.cell(x, y)), x, y)
        print(controller.playgroundToString)
      }
      buton.layoutX = 640
      buton.layoutY = 100
      content = List(map, buton, textinfo)
    }
  }



  def drawTexture(cell: Cell): Node = {
    val typ = cell match {
      case f: HeroCell => new Image("file:hero.png")
      case Leer() => new Image("file:grass.jpg")
      case Stop() => new Image("file:berg.jpg")
      case f: EnemyCell => new Image("file:drake.jpg")
      case f: Soldier => new Image("file:lich.jpg")
    }
    new ImageView(typ)
  }
}