package hu.bme.aut.highlowgame.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    viewModel: GameViewModel = viewModel()
) {
    var myNumber by remember { mutableStateOf("") }
    var resultText by remember { mutableStateOf("") }

    var isErrorState by remember { mutableStateOf(true) }

    fun validateInput(input: String) {
        if (input.isEmpty()) {
            isErrorState = true
            return
        }

        val allDigit = input.all { char -> char.isDigit() }
        isErrorState = !allDigit
    }

    Column(modifier = modifier) {
        OutlinedTextField(
            label = { Text("Enter a number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            value = "$myNumber",
            onValueChange = {
                myNumber = it
                validateInput(myNumber)
            },
            isError = isErrorState,
            supportingText = {
                if (isErrorState) {
                    Text(text = "Please enter a valid number")
                }
            },
            trailingIcon = {
                if (isErrorState)
                    Icon(Icons.Filled.Warning,
                        "error", tint = MaterialTheme.colorScheme.error)
            }

        )

        Button(
            enabled = !isErrorState,
            onClick = {
                val myNum = myNumber.toInt()
                if (viewModel.generatedNum == myNum) {
                    resultText = "You Won"
                } else if (viewModel.generatedNum < myNum) {
                    resultText = "The number is lower"
                } else if (viewModel.generatedNum > myNum) {
                    resultText = "The number is higher"
                }
            }) {
            Text("Guess")
        }

        Text(text = resultText)
    }
}