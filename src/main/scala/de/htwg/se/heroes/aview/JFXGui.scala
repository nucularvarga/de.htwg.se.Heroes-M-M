package de.htwg.se.heroes.aview

import de.htwg.se.heroes.controllerComponent.controllerBaseImpl.gamemode.{CombatMode, UIEvent}
import de.htwg.se.heroes.controllerComponent.controllerBaseImpl.gamemode.UIEvent.UIEvent
import scalafx.Includes._
import scalafx.application.{JFXApp, Platform}
import scalafx.scene.{Node, Scene, SceneAntialiasing, SubScene}
import de.htwg.se.heroes.controllerComponent._
import de.htwg.se.heroes.model.fieldComponent.fieldBaseImpl._
import de.htwg.se.heroes.model.soldier.soldierBaseImpl.{MeleeSoldier, RangeSoldier, Soldier}
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.{Cell => _, _}
import scalafx.application.JFXApp.PrimaryStage
import scalafx.beans.property.ReadOnlyDoubleProperty
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.{BorderPane, GridPane, Priority, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.text.Text

import scala.swing.Reactor


class JFXGui(controller: ControllerInterface) extends JFXApp with Reactor {

  listenTo(controller)

  reactions += {
    case e: FieldChanged => drawScene
    case e: GameStart => onGameStart
    case e: ViewChanged => drawView
    case e: Win => drawFinished
  }

  onGameStart

  stage = new PrimaryStage {
    outer =>
    title = "Heroes of Might and Magic"
    scene = new Scene(1020, 800) {
      root = new BorderPane {
        fill = Color.Brown
        add( 1, 0)
        center = createView()
      }
      //fullScreen_=(true)
    }
  }
/*
  def getSubScene = {
    val javaSubScene = stage.scene().lookup("#sub").asInstanceOf[javafx.scene.SubScene]
    new SubScene(javaSubScene)
  }
*/
  // creategame window
  def createView(): BorderPane = {
    new BorderPane {
      center = new SubScene(1020, 800) {
        id = "sub"
        fill = Color.Green
        //this.width.bind(boundWidth.add(0))
        //this.height.bind(boundHeight.add(-20))
        val startGame = new GridPane {
          private val label = new Label {
            text = "Wie viele Spieler?"
          }

          private val tog = new ToggleGroup()

          private val radioButton1 = new RadioButton {
            text = "Solo Spiel"
            id = "1"
            toggleGroup = tog
          }

          private val radioButton2 = new RadioButton {
            text = "2 Spieler"
            id = "2"
            toggleGroup = tog
          }

          private val startButton = new Button {
            maxHeight_=(1020)
            maxWidth_=(765)
            graphic_=( new ImageView(new Image("file:wallpaper.jpg")))
            onAction = handle {
              controller.init(tog.selectedToggle.get.asInstanceOf[javafx.scene.control.ToggleButton].id())
            }
          }

          add(label, 1, 1)
          add(radioButton1, 1, 2)
          add(radioButton2, 1, 3)
          add(startButton, 1, 4)
        }

        content = startGame

      }
    }
  }


  def onGameStart = {
  }

  def drawFinished = {
    Platform.runLater {
      //val subScene = getSubScene
      //val basicContent = createBasic3dContent  MODPROG github
      stage.scene = new Scene(1024, 800) {
        fill = Color.Brown
        root = new VBox {
          children = Seq(
            button( "Winner is: " + controller.getMode.playlist.getPlayer, textInputDialog),
            new Button {
              text = "exit"
              onAction = handle {
                System.exit(0)
              }
            }
          )
        }
      }
    }
  }

  def button[R](text: String, action: () => R) = new Button(text) {
    onAction = handle {action()}
    alignmentInParent = Pos.Center
    hgrow = Priority.Always
    maxWidth = Double.MaxValue
    padding = Insets(7)
  }

  def exitDialog(): Unit = {
    val dialog = new TextInputDialog(defaultValue = "walter") {
      initOwner(stage)
      title = "Text Input Dialog"
      headerText = "Look, a Text Input Dialog."
      contentText = "Please enter your name:"
    }
  }

  def textInputDialog(): Unit = {
    val dialog = new TextInputDialog(defaultValue = "walter") {
      initOwner(stage)
      title = "Text Input Dialog"
      headerText = "Look, a Text Input Dialog."
      contentText = "Please enter your name:"
    }
  }

  def drawView = {
    Platform.runLater {
      //val subScene = getSubScene
      //val basicContent = createBasic3dContent  MODPROG github
      stage.scene = new Scene(1024, 800) {
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
          } add(drawTexture(controller.getMatrixCell(x,y)), x, y)
        }


        val buton = new GridPane {
          private val buyfield = new TextField {
            maxWidth_=(40)
            text = "0"
          }

          private val buytyp = new TextField {
            maxWidth_=(40)
            text = "0"
          }

          private val buybutton = new Button {
            text = "Kaufen"
            onAction = handle {
              controller.openShop(getTyp(buytyp.text()), buyfield.text().toInt)
              textinfo.text = controller.getMessage
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

          private val save = new Button {
            text = "save"
            onAction = handle {
              controller.save
            }
          }

          private val load = new Button {
            text = "load"
            onAction = handle {
              controller.load
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

          private val lookright = new Button {
            text = "lookright"
            onAction = handle {
              controller.show(UIEvent.MoveRight)
            }
          }

          private val lookleft = new Button {
            text = "lookleft"
            onAction = handle {
              controller.show(UIEvent.MoveLeft)
            }
          }

          private val lookdown = new Button {
            text = "lookdown"
            onAction = handle {
              controller.show(UIEvent.MoveDown)
            }
          }

          private val looup = new Button {
            text = "looup"
            onAction = handle {
              controller.show(UIEvent.MoveUp)
            }
          }
          add(exit, 1, 0)
          add(up, 1, 1)
          add(right, 2, 2)
          add(Left, 0, 2)
          add(Down, 1, 2)
          add(buyfield, 3, 3)
          add(buybutton, 4, 3)
          add(buytyp, 2,3)
          add(info, 5, 3)
          add(revert, 4, 4)
          add(save, 2, 4)
          add(load, 2, 5)
          add(looup, 2, 7)
          add(lookdown, 2, 8)
          add(lookleft, 1, 8)
          add(lookright, 3, 8)
        }


        buton.layoutX = 640
        buton.layoutY = 100
        content = List(map, buton, textinfo)
      }
    }
  }

  def addEnemy(xs:Int, ys:Int): Node = {
    new ImageView {
      image = new Image("file:drake.jpg")
      onMousePressed = handle {
        controller.selectEnemy(xs,ys)
      }
    }
  }

  def drawScene = {
    Platform.runLater {
      //val subScene = getSubScene
      //val basicContent = createBasic3dContent  MODPROG github
      stage.scene = new Scene(1024, 800) {
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
          } {
            if(controller.getCell(x, y) match {
              case f:EnemyCell => controller.getMode match {case d:CombatMode => true case _ => false}
              case _ => false
            }) add(addEnemy(x,y), x, y)
            else
              add(drawTexture(controller.getCell(x,y)), x, y)
          }
        }


        val buton = new GridPane {
          private val buyfield = new TextField {
            maxWidth_=(40)
            text = "0"
          }

          private val buytyp = new TextField {
            maxWidth_=(40)
            text = "0"
          }

          private val buybutton = new Button {
            text = "Kaufen"
            onAction = handle {
              controller.openShop(getTyp(buytyp.text()), buyfield.text().toInt)
              textinfo.text = controller.getMessage
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

          private val save = new Button {
            text = "save"
            onAction = handle {
              controller.save
            }
          }

          private val load = new Button {
            text = "load"
            onAction = handle {
              controller.load
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
            graphic_=(new ImageView(new Image("file:unten.png")))
            onAction = handle {
              controller.action(UIEvent.MoveDown)
            }
          }

          private val lookright = new Button {
            graphic_=(new ImageView(new Image("file:rechts.png")))
            onAction = handle {
              controller.show(UIEvent.MoveRight)
            }
          }

          private val lookleft = new Button {
            graphic_=(new ImageView(new Image("file:links.png")))
            onAction = handle {
              controller.show(UIEvent.MoveLeft)
            }
          }

          private val lookdown = new Button {
            graphic_=(new ImageView(new Image("file:unten.png")))
            onAction = handle {
              controller.show(UIEvent.MoveDown)
            }
          }

          private val looup = new Button {
            graphic_=(new ImageView(new Image("file:hoch.png")))
            onAction = handle {
              controller.show(UIEvent.MoveUp)
            }
          }
          add(exit, 5, 0)
          add(up, 1, 1)
          add(right, 2, 2)
          add(Left, 0, 2)
          add(Down, 1, 2)
          add(buyfield, 1, 3)
          add(buytyp, 2,3)
          add(buybutton, 3, 3)
          add(info, 4, 3)
          add(revert, 0, 0)
          add(save, 3, 0)
          add(load, 2, 0)
          add(looup, 1, 4)
          add(lookdown, 1, 5)
          add(lookleft, 0, 5)
          add(lookright, 2, 5)
        }


        buton.layoutX = 640
        buton.layoutY = 100
        content = List(map, buton, textinfo)
      }
    }
  }

  def getTyp(input: String): UIEvent = {
    input match  {
      case "m" => UIEvent.BuyMelee
      case "r" => UIEvent.BuyRange
      case _ => UIEvent.BuyMelee
    }
  }

  def drawTexture(cell: Cell): Node = {
    val typ = cell match {
      case f: HeroCell => new Image("file:hero.png")
      case Leer() => new Image("file:grass.jpg")
      case Stop() => new Image("file:berg.jpg")
      case GoldCell() => new Image("file:gold.jpg")
      case f: EnemyCell => new Image("file:drake.jpg")
      case f: MeleeSoldier => new Image("file:lich.jpg")
      case f: RangeSoldier => new Image("file:gold.jpg")
    }
    new ImageView(typ)
  }

}