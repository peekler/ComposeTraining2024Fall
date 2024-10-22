package hu.ait.studentgradedemoadvanced

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import hu.ait.studentgradedemoadvanced.ui.theme.StudentGradeDemoAdvancedTheme
import hu.bme.aut.studentgraderoomdemo.ui.screen.GradeScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudentGradeDemoAdvancedTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GradeScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}