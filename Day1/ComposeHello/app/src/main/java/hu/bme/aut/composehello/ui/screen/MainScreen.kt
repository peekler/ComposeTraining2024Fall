package hu.bme.aut.composehello.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var showAlertDialog by remember { mutableStateOf(false) }
    

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        if (showAlertDialog) {
            ShowMessageDialog(
                onDismiss = { showAlertDialog = false },
                onConfirm = { showAlertDialog = false  }
            )
        }

        Text(
            "Hello Text",
            modifier = Modifier.weight(1f)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Green),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    showAlertDialog = true
                },
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 15.dp)
            ) { Text("Press me") }
            Button(
                onClick = {},
                modifier = Modifier.fillMaxHeight()
            ) { Text("Press me") }
            Button(
                onClick = {},
                modifier = Modifier.fillMaxHeight()
            ) { Text("Press me") }
        }
        Text(
            "Hello Text",
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ShowMessageDialog(
    text: String = "Hello dialog",
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) { Text("Confirm") }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text("Cancel")
            }
        },
        title = { Text("Title") },
        text = { Text("$text") }
    )
}