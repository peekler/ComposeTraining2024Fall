package hu.bme.aut.highlowgame.ui.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class GameViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    var generatedNum = 0
    var upperBund = 3

    init {
        var maxArg = savedStateHandle.get<String>("maxnum") ?: "3"
        upperBund = maxArg.toInt()
        generateNewNumber()
    }

    private fun generateNewNumber() {
        generatedNum = (1..upperBund).random()
    }
}