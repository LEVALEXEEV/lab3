package lev.lab3

import javafx.scene.control.Button
import javafx.scene.layout.BorderPane
import javafx.scene.paint.Color
import tornadofx.*

class vid : View(), BoardListener {


    private val board = Board().start()

    private val chipListener = BasedChipListener(board)
    private val boardListener = BasedBoardListener(board)

    override val root = BorderPane()

    init {
        title = "Shashki"
        board.registerListener(this)
        updateBoard()
    }

    override fun boardClicked(cell: Cell) {
        TODO("Not yet implemented")
    }

    override fun update() {
        updateBoard()
    }

    private fun updateBoard() {
        val buttons = mutableMapOf<Cell, Button>()
        with(root) {
            center {
                style = "-fx-background-color: brown"
                gridpane {
                    val dimension = Dimension(72.0, Dimension.LinearUnits.px)
                    for (row in 0 until 8) {
                        row {
                            for (column in 0 until 8) {
                                val cell = board.cells[row][column]
                                val button = button {
                                    style {
                                        backgroundColor += board.cells[row][column].cellColor
                                        minWidth = dimension
                                        minHeight = dimension
                                    }
                                }
                                button.action {
                                    boardListener.boardClicked(cell)
                                }
                                buttons[cell] = button
                                if (board.cells[row][column].chip != null) {
                                    buttons[cell]?.apply {
                                        button {
                                            style = when (board.cells[row][column].cellColor) {
                                                Color.BROWN -> "-fx-background-color: brown"
                                                Color.RED -> "-fx-background-color: red"
                                                Color.PINK -> "-fx-background-color: pink"
                                                else -> "-fx-background-color: yellow"
                                            }
                                            graphic = circle(radius = 20.0) {
                                                fill = board.cells[row][column].chip?.color
                                            }
                                        }.action {
                                            chipListener.chipClicked(cell)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
