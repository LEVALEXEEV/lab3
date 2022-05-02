package lev.lab3

import javafx.scene.paint.Color

var bcn = 12
var wcn = 12

class Board {

    private val width = 8
    private val height = 8
    private var turn = Move.WHITE

    private var listener: BoardListener? = null
    fun registerListener(listener: BoardListener) {
        this.listener = listener
    }

    var cells = mutableListOf<List<Cell>>()

    fun start(): Board {
        for (row in 0 until width) {
            val list = mutableListOf<Cell>()
            for (column in 0 until height) {
                val c = if ((row % 2 == 0 && column % 2 == 1) || (row % 2 == 1 && column % 2 == 0))
                    Color.BROWN
                else
                    Color.IVORY
                val cell = Cell(row, column, c)
                if (bcn != 0 && c == Color.BROWN) {
                    cell.chip = Chip(false, Color.BLACK)
                    bcn--
                }
                if (wcn != 0 && c == Color.BROWN && row >= 5) {
                    cell.chip = Chip(false, Color.WHITE)
                    wcn--
                }
                list.add(cell)
            }
            cells.add(list)
        }
        return this
    }

    fun clearBoard(){
        for (i in cells.indices){
            for (j in cells[i].indices){
                val c = if ((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0))
                    Color.BROWN
                else
                    Color.IVORY
                cells[i][j].cellColor = c
            }
        }
    }

    fun makeTurn(cell: Cell) {
        clearBoard()
        var x = -1
        val t: Color
        if (turn == Move.WHITE)
            t = Color.WHITE
        else {
            t = Color.BLACK
            x = 1
        }
        if (t == cell.chip?.color) {
            cells[cell.x][cell.y].cellColor = Color.YELLOW
            var goal1 = Cell(cell.x + x, cell.y + 1, Color.BROWN)
            var goal2 = Cell(cell.x + x, cell.y - 1, Color.BROWN)
            if (goal1.isInside()){
                if (cells[goal1.x][goal1.y].chip == null) cells[goal1.x][goal1.y].cellColor = Color.RED
                else if (cells[goal1.x][goal1.y].chip?.color != t){
                    goal1 = Cell(goal1.x + x, goal1.y + 1, Color.BROWN)
                    if (goal1.isInside() && cells[goal1.x][goal1.y].chip == null) {
                        cells[goal1.x - x][goal1.y - 1].cellColor = Color.PINK
                        cells[goal1.x][goal1.y].cellColor = Color.RED
                    }
                }
            }
            if (goal2.isInside()){
                if (cells[goal2.x][goal2.y].chip == null) cells[goal2.x][goal2.y].cellColor = Color.RED
                else if (cells[goal2.x][goal2.y].chip?.color != t){
                    goal2 = Cell(goal2.x + x, goal2.y - 1, Color.BROWN)
                    if (goal2.isInside() && cells[goal2.x][goal2.y].chip == null) {
                        cells[goal2.x - x][goal2.y + 1].cellColor = Color.PINK
                        cells[goal2.x][goal2.y].cellColor = Color.RED
                    }
                }
            }
        }
        listener!!.update()
    }

    fun turnMade(cell: Cell){
        var condition = false
        if (cell.cellColor == Color.RED){
            val t: Color = if (turn == Move.WHITE)
                Color.WHITE
            else {
                Color.BLACK
            }
            if ((turn == Move.BLACK && cell.isUp()) || (turn == Move.WHITE && cell.isDown())) condition = true
            cells[cell.x][cell.y].chip = Chip(condition, t)
            for (i in cells.indices){
                for (j in cells[i].indices){
                    if (cells[i][j].cellColor == Color.YELLOW || cells[i][j].cellColor == Color.PINK) {
                        cells[i][j].cellColor = Color.BROWN
                        cells[i][j].chip = null
                    }
                }
            }
            turn = turn.opposite()
            clearBoard()
        }
        listener!!.update()
    }

}