package lev.lab3

interface BoardListener {
    fun update()
    fun boardClicked(cell: Cell)
}