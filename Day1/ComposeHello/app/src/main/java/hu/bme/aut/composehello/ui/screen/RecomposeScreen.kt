package hu.bme.aut.composehello.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun RecomposeScreen(modifier: Modifier = Modifier) {

    var clickCount by remember{mutableStateOf(1)}


    Column(
        modifier = modifier
    ) {
        Button(onClick = {
            clickCount++
        }) {
            Log.d("TAG_COMPOSE","recompose occured in button")
            Text(text = "Press me")
        }
        Log.d("TAG_COMPOSE","recompose occured outside")
        Text(text = "Hello $clickCount!")
    }

}