package de.htwg.se.heroes.aview

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle
import de.htwg.se.heroes.controller.Controller
import de.htwg.se.heroes.main.controller
import de.htwg.se.heroes.util.Observer
import scalafx.application
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.image.{Image, ImageView}
import scalafx.stage.{Stage, WindowEvent}


//class CellClicked(val row: Int, val column: Int) extends Event



object GUI extends JFXApp {

  stage = new JFXApp.PrimaryStage {
    title = "Hello Stage"
    fullScreen_=(true)
   // print(fullScreen.value)
    /*scene = new Scene {
      fill = LightGreen
      content = new Rectangle {
        x = 25
        y = 40
        width = 100
        height = 100
        fill <== when(hover) choose Green otherwise Red
      }
    }*/
    scene = drawTexture(new Image("file:src\\main\\scala\\de\\htwg\\se\\heroes\\aview\\teil1_3s.png"))
  }


  def drawTexture(typ: Image): Scene = {
    new Scene(600,600) {
      content = new ImageView(typ)
    }
  }


}