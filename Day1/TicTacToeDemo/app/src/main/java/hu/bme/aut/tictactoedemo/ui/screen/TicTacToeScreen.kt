package hu.bme.aut.tictactoedemo.ui.screen

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.viewmodel.compose.viewModel
import hu.bme.aut.tictactoedemo.R

@Composable
fun TicTacToeScreen(modifier: Modifier,
                    ticTacToeViewModel: TicTacToeViewModel = viewModel()) {
    Column(modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Welcome")

        Text("Current Player: ${ticTacToeViewModel.currentPlayer}")

        TicTacToeBoard(
            board = ticTacToeViewModel.board,
            onCellClick = { boardCell ->
                ticTacToeViewModel.onCellClicked(boardCell)
            }
        )

        OutlinedButton(onClick = {
            ticTacToeViewModel.resetGame()
        }) {
            Text("Reset")
        }
    }
}

@Composable
fun TicTacToeBoard(
    board: Array<Array<Player?>>,
    onCellClick: (BoardCell) -> Unit
) {
    val imgBackGround = ImageBitmap.imageResource(R.drawable.grass)

    val textMeasurer = rememberTextMeasurer()

    Canvas(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .aspectRatio(1.0f)
            .pointerInput(key1 = Unit) {
                detectTapGestures { clickCoord ->
                    Log.d(
                        "TAG_CLICK",
                        "Click: ${clickCoord.x} ${clickCoord.y}"
                    )

                    val row = (clickCoord.y / (size.height / 3)).toInt()
                    val col = (clickCoord.x / (size.width / 3)).toInt()

                    onCellClick(BoardCell(row, col))
                }
            }
    ) {
        val gridSize = size.minDimension
        val thirdSize = gridSize / 3

        drawImage(
            image = imgBackGround,
            srcOffset = IntOffset(0,0),
            srcSize = IntSize(imgBackGround.width, imgBackGround.height),
            dstOffset = IntOffset(thirdSize.toInt(),0),
            dstSize = IntSize(gridSize.toInt()/3, gridSize.toInt()/3)
        )

        // draw text
        val textLayoutResult: TextLayoutResult =
            textMeasurer.measure(
                text = "8",
                style = TextStyle(fontSize = thirdSize.toSp(),
                    fontWeight = FontWeight.Bold)
            )
        val textSize = textLayoutResult.size
        drawText(
            textLayoutResult = textLayoutResult,
            topLeft = Offset(
                x  = thirdSize/2 - textSize.width/2,
                y = thirdSize/2 - textSize.height/2
            )
        )

        // draw the grid
        for (i in 1..2) {
            drawLine(
                color = Color.Black,
                strokeWidth = 5f,
                start = Offset(thirdSize * i, 0f),
                end = Offset(thirdSize * i, gridSize)
            )

            drawLine(
                color = Color.Black,
                strokeWidth = 5f,
                start = Offset(0f, thirdSize * i),
                end = Offset(gridSize, thirdSize * i)
            )
        }

        // Draw the X and O
        for (row in 0..2) {
            for (col in 0..2) {
                val player = board[row][col]

                if (player != null) {
                    val centerX = col * thirdSize + thirdSize / 2
                    val centerY = row * thirdSize + thirdSize / 2

                    if (player == Player.X) {
                        drawLine(
                            color = Color.Black,
                            strokeWidth = 8f,
                            pathEffect = PathEffect.cornerPathEffect(4f),
                            start = androidx.compose.ui.geometry.Offset(
                                centerX - thirdSize / 4,
                                centerY - thirdSize / 4
                            ),
                            end = androidx.compose.ui.geometry.Offset(
                                centerX + thirdSize / 4,
                                centerY + thirdSize / 4
                            ),
                        )
                        drawLine(
                            color = Color.Black,
                            strokeWidth = 8f,
                            pathEffect = PathEffect.cornerPathEffect(4f),
                            start = androidx.compose.ui.geometry.Offset(
                                centerX + thirdSize / 4,
                                centerY - thirdSize / 4
                            ),
                            end = androidx.compose.ui.geometry.Offset(
                                centerX - thirdSize / 4,
                                centerY + thirdSize / 4
                            ),
                        )
                    } else {
                        drawCircle(
                            color = Color.Black,
                            style = Stroke(width = 8f),
                            center = androidx.compose.ui.geometry.Offset(centerX, centerY),
                            radius = (thirdSize / 4),
                        )
                    }
                }
            }
        }

    }
}