package hu.bme.aut.highlowgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hu.bme.aut.highlowgame.ui.screen.GameScreen
import hu.bme.aut.highlowgame.ui.screen.MainScreen
import hu.bme.aut.highlowgame.ui.theme.HighLowGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HighLowGameTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //GameScreen(modifier = Modifier.padding(innerPadding))
                    MainNavHost(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "mainscreen"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("mainscreen") {
            MainScreen(
                { navController.navigate("gamescreen?maxnum=100") }
            )
        }
        composable("gamescreen?maxnum={maxnum}") {
            GameScreen() }
    }
}

