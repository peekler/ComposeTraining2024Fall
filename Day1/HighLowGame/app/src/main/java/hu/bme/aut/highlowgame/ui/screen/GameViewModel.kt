package hu.bme.aut.highlowgame.ui.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import hu.bme.aut.highlowgame.ui.navigation.Game

class GameViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    var generatedNum = 0
    var upperBund = 3

    init {
        upperBund = savedStateHandle.toRoute<Game>().maxNum
        generateNewNumber()
    }

    private fun generateNewNumber() {
        generatedNum = (1..upperBund).random()
    }
}