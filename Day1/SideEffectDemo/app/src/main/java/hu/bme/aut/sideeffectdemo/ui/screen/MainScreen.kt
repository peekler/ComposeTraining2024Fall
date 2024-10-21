package hu.bme.aut.sideeffectdemo.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun MainContainer() {

    var count by remember { mutableStateOf(0) }
    var count2 by remember { mutableStateOf(0) }

    SideEffect {
        Log.d("TAG_SIDE", "SideEffect called")
    }

    LaunchedEffect(key1 = Unit) {
        Log.d("TAG_SIDE", "LaunchedEffect called orientation")
    }

    DisposableEffect(key1 = Unit) {
        //Log.d("TAG_SIDE", "DisposableEffect LAUNCH called")

        onDispose {
            Log.d("TAG_SIDE", "DisposableEffect ONDISPOSE called")
        }
    }

    Column()
    {
        Button(onClick = {
            count2++
        }) {
            Text("Demo")
        }
        Text("Count: $count2")
    }

}