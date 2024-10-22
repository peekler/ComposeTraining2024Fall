package hu.bme.aut.compatibilitydemo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_launch)

        val composeView = findViewById<ComposeView>(R.id.composeView)
        composeView.setContent {
            MainScreen()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

@Composable
fun MainScreen() {
    Column {
        Text("Backbase")
        Text("Backbase")
        Text("Backbase")
        Text("Backbase")
    }
}