package lev.lab3
import javafx.scene.paint.Color

class Chip(var condition: Boolean, var color: Color) {
    fun queen() = Chip(true, this.color)
}

enum class Move {
    BLACK, WHITE;

    open fun opposite(): Move {
        return if (this == WHITE) BLACK else WHITE
    }
}