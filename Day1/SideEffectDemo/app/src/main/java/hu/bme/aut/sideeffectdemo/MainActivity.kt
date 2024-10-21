package hu.bme.aut.sideeffectdemo

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import hu.bme.aut.sideeffectdemo.ui.screen.MainContainer
import hu.bme.aut.sideeffectdemo.ui.theme.SideEffectDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            SideEffectDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ConfigChangeScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }


}


@Composable
fun ConfigChangeScreen(modifier: Modifier = Modifier) {
    val configuration = LocalConfiguration.current

    val orientation = remember(configuration.orientation) {
        when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> "Landscape"
            Configuration.ORIENTATION_PORTRAIT -> "Portrait"
            else -> "Undefined"
        }
    }

    Column(modifier = modifier) {
        Text(
            text = "Orientation: $orientation"
        )
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var showContent by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Button(
            onClick = {
                showContent = !showContent
            }
        ) {
            Text("show/hide")
        }

        if (showContent) {
            MainContainer()
        }
    }
}