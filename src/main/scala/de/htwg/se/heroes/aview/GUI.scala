package de.htwg.se.heroes.aview

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.{Node, Scene}
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
    stage = new JFXApp.PrimaryStage {
      outer =>
      title = "Heroes of Might and Magic"
      scene = new Scene(1024, 590) {
        val buton = new GridPane {
          private val Down = new Button {
            text = "Down"
            onAction = handle {
              controller.init()
            }
          }
          add(Down, 1, 0)
        }
        //fullScreen_=(true)
        content = buton
      }
    }


  def drawScene: Unit = {
    println("SCENE UPDATE")
    stage.scene = new Scene(1024, 590) {

      private val textinfo = new Label {
      }
      textinfo.setPrefWidth(250)
      textinfo.setPrefHeight(50)
      textinfo.setText(controller.messanger.getMsg)
      textinfo.layoutX = 650
      textinfo.layoutY = 500
      val map = new GridPane {
        padding = Insets(5)
        vgap = 0
        hgap = 0

        for {
          y <- 0 until 9
          x <- 0 until 9
        } add(drawTexture(controller.mode.cell(x, y)), x, y)
      }


      val buton = new GridPane {
        private val buyfield = new TextField {
          text = "0"
        }

        private val buybutton = new Button {
          text = "Kaufen"
          onAction = handle {
            controller.openShop(buyfield.text().toInt)
            textinfo.text = controller.messanger.getMsg
          }
        }

        private val exit = new Button {
          text = "exit"
          onAction = handle {
          }
        }

        private val info = new Button {
          text = "info"
          onAction = handle {
            controller.showStats()
            textinfo.text = controller.messanger.getMsg
          }
        }
        private val up = new Button {
          text = "Up"
          onAction = handle {
            controller.action(Event.MoveUp)
          }
        }

        private val right = new Button {
          text = "Right"
          onAction = handle {
            controller.action(Event.MoveRight)
          }
        }
        private val Left = new Button {
          text = "Left"
          onAction = handle {
            controller.action(Event.MoveLeft)
          }
        }

        private val revert = new Button {
          text = "Undo"
          onAction = handle {
            controller.undo
          }
        }

        private val Down = new Button {
          text = "Down"
          onAction = handle {
            controller.action(Event.MoveDown)
          }
        }
        add(exit, 1, 0)
        add(up, 1, 1)
        add(right, 2, 2)
        add(Left, 0, 2)
        add(Down, 1, 2)
        add(buyfield, 3, 3)
        add(buybutton, 4, 3)
        add(info, 5, 3)
        add(revert, 4, 4)
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