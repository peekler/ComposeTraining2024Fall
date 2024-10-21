package hu.bme.aut.composehello

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hu.bme.aut.composehello.ui.screen.MainScreen
import hu.bme.aut.composehello.ui.screen.RecomposeScreen
import hu.bme.aut.composehello.ui.screen.UnitsConverterScreen
import hu.bme.aut.composehello.ui.theme.ComposeHelloTheme
import java.util.Date

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            ComposeHelloTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    /*Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )*/

                    //MainScreen(modifier = Modifier.padding(innerPadding))
                    //UnitsConverterScreen(modifier = Modifier.padding(innerPadding))

                    RecomposeScreen(modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var currentDate by remember { mutableStateOf("") }
    var textName by remember { mutableStateOf("") }

    Column(
        modifier = modifier
    ) {
        Text(
            text = "Hello $textName Date: $currentDate! ",
            modifier = Modifier.clickable {
                currentDate = Date(System.currentTimeMillis()).toString()
            },
            fontSize = 30.sp
        )
        OutlinedTextField(
            value = "$textName",
            onValueChange = { inputContent ->
                textName = inputContent
            }
        )
        Button(
            onClick = {
                currentDate = Date(System.currentTimeMillis()).toString()
            }
        ) {
            Text(text = "Click Me")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeHelloTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    ComposeHelloTheme {
        Greeting("Backbase")
    }
}