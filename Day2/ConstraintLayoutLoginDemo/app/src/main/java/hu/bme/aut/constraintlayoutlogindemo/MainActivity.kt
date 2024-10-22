package hu.bme.aut.constraintlayoutlogindemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import hu.bme.aut.constraintlayoutlogindemo.ui.theme.ConstraintLayoutLoginDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ConstraintLayoutLoginDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ConstraintLayoutDemo2(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ConstraintLayoutDemo2(modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = modifier.fillMaxSize()
    ) {
        val centerGuideline = createGuidelineFromTop(0.5f)

        val (title, aboutBtn, helpBtn, name, password, loginBtn, regBtn) = createRefs()

        Text("Welcome",
            Modifier.constrainAs(title) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        Button(
            onClick = {  },
            modifier = Modifier.constrainAs(aboutBtn) {
                bottom.linkTo(parent.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
            }
        ) { Text("About") }

        Button(
            onClick = { /* Do something */ },
            modifier = Modifier.constrainAs(helpBtn) {
                bottom.linkTo(parent.bottom, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
            }
        ) { Text("Help") }

        OutlinedTextField(
            label = { Text("Name") },
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(0.8f).constrainAs(name) {
                bottom.linkTo(password.top, margin = 6.dp)
                start.linkTo(password.start)
                end.linkTo(password.end)
            }
        )

        OutlinedTextField(
            label = { Text("Password") },
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(0.8f).constrainAs(password) {
                top.linkTo(centerGuideline)
                bottom.linkTo(centerGuideline)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        Button(
            onClick = { /* Do something */ },
            modifier = Modifier.constrainAs(loginBtn) {
                top.linkTo(password.bottom, margin = 16.dp)
                start.linkTo(password.start)
            }
        ) { Text("Login") }

        Button(
            onClick = { /* Do something */ },
            modifier = Modifier.constrainAs(regBtn) {
                top.linkTo(password.bottom, margin = 16.dp)
                end.linkTo(password.end)
            }
        ) { Text("Reg") }



    }
}

@Composable
fun ConstraintLayoutDemo(modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = modifier.fillMaxSize().background(Color.Green)
    ) {

        val startGuideline = createGuidelineFromStart(0.3f)
        val centerGuideline = createGuidelineFromTop(0.5f)

        val (title, name, password, loginBtn, regBtn, aboutBtn, helpBtn) = createRefs()

        Text("Welcome",
            Modifier.constrainAs(title) {
                bottom.linkTo(parent.top, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        Button(
            onClick = {  },
            modifier = Modifier.constrainAs(aboutBtn) {
                bottom.linkTo(parent.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
            }
        ) { Text("About") }

        Button(
            onClick = { /* Do something */ },
            modifier = Modifier.constrainAs(aboutBtn) {
                bottom.linkTo(parent.bottom, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
            }
        ) { Text("Help") }

        OutlinedTextField(
            label = { Text("Name") },
            value = "",
            onValueChange = {},
            modifier = Modifier.constrainAs(name) {
                top.linkTo(centerGuideline)
                bottom.linkTo(centerGuideline)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

    }
}