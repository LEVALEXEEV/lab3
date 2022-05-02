package lev.lab3
import javafx.scene.paint.Color

class Cell(val x: Int, val y: Int, var cellColor: Color) {

    var chip: Chip? = null

    fun isInside(): Boolean = (this.x < 8) && (this.y < 8) && (this.x >= 0) && (this.y >= 0)
    fun isUp(): Boolean = (this.x == 8)
    fun isDown(): Boolean = (this.x == 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other is Cell) {
            return x == other.x && y == other.y
        }
        return false
    }

    override fun hashCode(): Int {
        var result = 11
        result = 19 * result + x
        return 19 * result + y
    }

    override fun toString(): String {
        return "$x:$y"
    }
}