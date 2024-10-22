package hu.bme.aut.highlowgame.ui.navigation

import kotlinx.serialization.Serializable

// Define a home route that doesn't take any arguments
@kotlinx.serialization.Serializable
object Main

@kotlinx.serialization.Serializable
object About

// Define a profile route that takes an ID
@Serializable
data class Game(val maxNum: Int)