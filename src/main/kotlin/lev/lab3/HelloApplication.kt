package lev.lab3

import javafx.application.Application
import javafx.scene.paint.Color
import javafx.stage.Stage
import tornadofx.App

class HelloApplication : App(vid::class) {
    override fun start(stage: Stage) {
        stage.show()
        super.start(stage)
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}