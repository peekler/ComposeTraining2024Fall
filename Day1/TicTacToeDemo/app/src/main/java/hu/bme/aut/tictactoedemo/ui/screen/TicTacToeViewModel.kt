package hu.bme.aut.tictactoedemo.ui.screen

import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

enum class Player{
    X, O
}

data class BoardCell(val row: Int, val col: Int)

class TicTacToeViewModel : ViewModel() {
    var board by mutableStateOf(
        Array(3) { Array(3) { null as Player? } })

    var currentPlayer by mutableStateOf(Player.X)

    fun onCellClicked(cell: BoardCell) {
        if (board[cell.row][cell.col] == null) {
            val newBoard = board.copyOf()
            newBoard[cell.row][cell.col] = currentPlayer
            board = newBoard


            currentPlayer =
                if (currentPlayer == Player.X) Player.O else Player.X
        }
    }

    fun resetGame() {
        board = Array(3) { Array(3) { null as Player? } }
        currentPlayer = Player.X
    }
}