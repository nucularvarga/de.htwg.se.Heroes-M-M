package de.htwg.se.heroes.aview

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle
import de.htwg.se.heroes.controller.Controller
import de.htwg.se.heroes.util.Observer
import scalafx.application.JFXApp.PrimaryStage
import scalafx.stage.Stage


//class CellClicked(val row: Int, val column: Int) extends Event

class GUI extends JFXApp {

  def start(): Unit = {
    // initialization here
    stage = new PrimaryStage {
      title.value = "HelloStage"
      width = 600
      height = 450
      scene = new Scene {
        fill = LightGreen
        content = new Rectangle {
          x = 25
          y = 40
          width = 100
          height = 100
          fill <== when(hover) choose Green otherwise Red
        }
      }
    }
  }
}