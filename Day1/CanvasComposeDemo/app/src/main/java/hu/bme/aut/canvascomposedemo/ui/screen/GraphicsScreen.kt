package hu.bme.aut.canvascomposedemo.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import hu.bme.aut.canvascomposedemo.R
import kotlin.math.roundToInt

@Composable
fun GraphicsScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        var sliderPositionA by remember { mutableFloatStateOf(0f) }
        var sliderPositionB by remember { mutableFloatStateOf(0f) }
        var sliderPositionC by remember { mutableFloatStateOf(0f) }

        Text(text = sliderPositionA.toString())
        Slider(
            value = sliderPositionA,
            onValueChange = { sliderPositionA = it.roundToInt().toFloat()},
            steps = 100,
            valueRange = 0f..360f
        )

        Text(text = sliderPositionB.toString())
        Slider(
            value = sliderPositionB,
            onValueChange = { sliderPositionB = it.roundToInt().toFloat() },
            steps = 100,
            valueRange = 0f..360f
        )

        Text(text = sliderPositionC.toString())
        Slider(
            value = sliderPositionC,
            onValueChange = { sliderPositionC = it.roundToInt().toFloat() },
            steps = 100,
            valueRange = 0f..360f
        )


        Image(
            painter = painterResource(id = R.drawable.card),
            contentDescription = "Card",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .graphicsLayer {
                    this.translationX = sliderPositionA.dp.toPx()
                    this.translationY = sliderPositionB.dp.toPx()
                    //this.rotationX = sliderPositionA
                    //this.rotationY = sliderPositionB
                    //this.rotationZ = sliderPositionC
                }
        )
    }

}