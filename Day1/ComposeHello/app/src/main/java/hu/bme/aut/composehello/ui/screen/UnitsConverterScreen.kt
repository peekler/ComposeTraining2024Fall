package hu.bme.aut.composehello.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun UnitsConverterScreen(modifier: Modifier) {
    var textContent by remember { mutableStateOf("0") }
    var result by remember { mutableStateOf("0") }
    var showResult by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        TextField(
            value = "$textContent",
            onValueChange = {
                textContent = it
            }
        )
        Button(
            onClick = {
                //1 km = 0.6214 miles
                result = (textContent.toDouble() * 0.6214).toString()
                showResult = true
            }
        ) {
            Text("Convert")
        }

        if (showResult) {
            Text("Result: $result")
        }
    }

    if (showResult) {
        ShowMessageDialog(result,
            onConfirm = {showResult = false},
            onDismiss = {showResult = false})
    }
}