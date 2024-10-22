package hu.bme.aut.compatibilitydemo

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import hu.bme.aut.compatibilitydemo.databinding.DemoLayoutBinding
import hu.bme.aut.compatibilitydemo.ui.theme.CompatibilityDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CompatibilityDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var myNum by remember { mutableStateOf(0) }

    Column(
        modifier = modifier
    ) {
        Text("Hello $name!")
        Button(onClick = {myNum++}) {
            Text("State change")
        }
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                val binding = DemoLayoutBinding.inflate(LayoutInflater.from(context))
                binding.btnDemo.setOnClickListener {
                    binding.tvDemo.text = "Rand: $myNum"
                }
                binding.root
            },
            update = { view ->
                DemoLayoutBinding.bind(view).tvDemo.text = "Rand: $myNum"
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CompatibilityDemoTheme {
        Greeting("Android")
    }
}