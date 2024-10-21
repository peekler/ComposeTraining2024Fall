package hu.bme.aut.highlowgame.ui.screen

import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    var generatedNum = 0

    init {
        generateNewNumber()
    }

    private fun generateNewNumber() {
        generatedNum = (1..10).random()
    }


}