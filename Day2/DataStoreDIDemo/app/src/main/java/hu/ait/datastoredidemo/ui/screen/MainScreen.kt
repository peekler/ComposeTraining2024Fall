package hu.ait.datastoredidemo.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainScreen(modifier: Modifier = Modifier,
               mainViewModel: MainViewModel = hiltViewModel()
) {
    val userName = mainViewModel.getUserName().collectAsState(initial = "empty")
    var newUserName by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        Text(text = "Hello ${userName.value}")

        TextField(
            value = newUserName,
            onValueChange = {
                newUserName = it
            }
        )
        OutlinedButton(
            onClick = {
                mainViewModel.updateUser(newUserName)
            }
        ) {
            Text("Save username")
        }

    }
}