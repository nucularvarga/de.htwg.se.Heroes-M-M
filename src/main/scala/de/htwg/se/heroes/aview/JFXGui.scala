package de.htwg.se.heroes.aview

import scalafx.Includes._
import scalafx.application.{JFXApp, Platform}
import scalafx.scene.{Node, Scene, SceneAntialiasing, SubScene}
import de.htwg.se.heroes.controller.{Controller, FieldChanged, GameStart, UIEvent}
import scalafx.geometry.Insets
import scalafx.scene.control.{Button, Label, TextArea, TextField}
import de.htwg.se.heroes.model._
import scalafx.application.JFXApp.PrimaryStage
import scalafx.beans.property.ReadOnlyDoubleProperty
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.{BorderPane, GridPane}
import scalafx.scene.paint.Color
import scalafx.scene.text.Text

import scala.swing.Reactor


class JFXGui(controller: Controller) extends JFXApp with Reactor {

  listenTo(controller)

  reactions += {
    case e: FieldChanged => drawScene
    case e: GameStart => onGameStart
  }

  onGameStart

    stage = new PrimaryStage {
      outer =>
      title = "Heroes of Might and Magic"
      scene = new Scene(1024, 590) {
        root = new BorderPane {
          fill = Color.Brown
          var tmpw = this.width
          var tmph = this.height
          private val Down = new Button {
            text = "Down"
            onAction = handle {
              controller.init()
            }
          }
          add( 1, 0)
          center = createView(tmpw, tmph)
        }
        //fullScreen_=(true)
      }
    }

  def getSubScene = {
    val javaSubScene = stage.scene().lookup("#sub").asInstanceOf[javafx.scene.SubScene]
    new SubScene(javaSubScene)
  }

  // creategame window
  def createView(boundWidth: ReadOnlyDoubleProperty, boundHeight: ReadOnlyDoubleProperty): BorderPane = {
    new BorderPane {
      center = new SubScene(boundWidth.get(), boundHeight.get(), true, SceneAntialiasing.Balanced) {
        id = "sub"
        fill = Color.Black
        this.width.bind(boundWidth.add(0))
        this.height.bind(boundHeight.add(-20))
      }
    }
  }


  def onGameStart = {

  }


  def setStatusText {
    val javaText = stage.scene().lookup("#statusText").asInstanceOf[javafx.scene.text.Text]
    val text = new Text(javaText)
    text.text = controller.messanger.getMsg
  }

  def drawScene = {
    Platform.runLater {

      stage.scene = new Scene(1024, 590) {
        fill = Color.Brown
        private val textinfo = new Text {
          id = "statusText"
        }
        //textinfo.setPrefWidth(250)
        //textinfo.setPrefHeight(50)
        //textinfo.setText(controller.messanger.getMsg)
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
              System.exit(0)
            }
          }

          private val info = new Button {
            text = "info"
            onAction = handle {
              controller.showStats()
            }
          }
          private val up = new Button {
            text = "Up"
            onAction = handle {
              controller.action(UIEvent.MoveUp)
            }
          }

          private val right = new Button {
            text = "Right"
            onAction = handle {
              controller.action(UIEvent.MoveRight)
            }
          }
          private val Left = new Button {
            text = "Left"
            onAction = handle {
              controller.action(UIEvent.MoveLeft)
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
              controller.action(UIEvent.MoveDown)
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