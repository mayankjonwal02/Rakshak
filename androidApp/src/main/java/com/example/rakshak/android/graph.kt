package com.example.rakshak.android

import android.app.Activity
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import kotlin.math.round
import kotlin.math.roundToInt

@Preview
@Composable
fun graph() {

//    var view = LocalView.current
//    var window = (view.context as Activity).window
//    window.statusBarColor = Color.Cyan.toArgb()
//    window.navigationBarColor = Color.Cyan.toArgb()
    var systemuicontroller = rememberSystemUiController()

    systemuicontroller.setNavigationBarColor(
        color = Color.Cyan,
        darkIcons = true
    )

    systemuicontroller.setStatusBarColor(
        color = Color.Cyan,
        darkIcons = true
    )

    val heartSoundData: List<Pair<Int, Double>> = listOf(
        Pair(1, 0.82),
        Pair(2, 0.93),
        Pair(3, 1.05),
        Pair(4, 1.15),
        Pair(5, 0.98), // Modified value
        Pair(6, 0.75),
        Pair(7, 0.92),
        Pair(8, 1.23),
        Pair(9, 1.14),
        Pair(10, 1.01)
    )

    var heartrate = listOf(
        Pair(1, 72.5),
        Pair(2, 68.2),
        Pair(3, 75.8),
        Pair(4, 81.3),
        Pair(5, 78.9),
        Pair(6, 67.4),
        Pair(7, 71.1),
        Pair(8, 89.6),
        Pair(9, 76.8),
        Pair(10, 82.2)
    )
    var heartsounddatamodified = heartSoundData.map { it ->
        Pair(it.first,it.second * 10)
    }
    val temperatureData: List<Pair<Int, Double>> = listOf(
        Pair(1, 25.5),
        Pair(2, 26.2),
        Pair(3, 25.8),
        Pair(4, 27.1),
        Pair(5, 26.7),
        Pair(6, 25.9),
        Pair(7, 24.8),
        Pair(8, 23.6),
        Pair(9, 24.5),
        Pair(10, 26.0)
    )

    val spo2Data: List<Pair<Int, Double>> = listOf(
        Pair(1, 98.5),
        Pair(2, 97.2),
        Pair(3, 99.0),
        Pair(4, 95.8),
        Pair(5, 98.0),
        Pair(6, 96.5),
        Pair(7, 97.8),
        Pair(8, 99.5),
        Pair(9, 96.8),
        Pair(10, 98.2)
    )

    var data : List<Pair<Int,Double>> by remember{ mutableStateOf(heartrate) }
    var heading by remember {
        mutableStateOf("Heart Rate")
    }


    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black), contentAlignment = Alignment.Center)
    {


        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.Black), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
            Text(text = heading, fontStyle = FontStyle.Normal, fontFamily = FontFamily.Default, fontSize = 30.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
            Spacer(modifier = Modifier.height(20.dp))
//            LaunchedEffect(heading) {
//                data = when (heading) {
//                    "Heart Rate" -> heartrate
//                    "Heart Sound" -> heartsounddatamodified
//                    "Spo2" -> spo2Data
//                    "Temperature" -> temperatureData
//                    else -> data
//                }
//            }
//            canvasgraph(data = data)
            Crossfade(targetState = heading) {
                it ->
                when(it)
                {
                    "Heart Rate" -> canvasgraph(data = heartrate)
                    "Heart Sound" -> canvasgraph(data = heartsounddatamodified)
                    "Spo2" -> canvasgraph(data = spo2Data)
                    "Temperature" -> canvasgraph(data = temperatureData)
                    else -> data
                }

            }

            Spacer(modifier = Modifier.height(30.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically,
                )  {
               OutlinedButton(onClick = { heading = "Heart Rate"
//                                         data = heartrate
                                        }, colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent, contentColor = Color.Cyan), border = BorderStroke(3.dp,
                   Color.Cyan)) {
                   Text(text = "Heart Rate")
               }

                OutlinedButton(onClick = { heading = "Heart Sound"
//                    data =heartsounddatamodified
                                         }, colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent, contentColor = Color.Cyan), border = BorderStroke(3.dp,
                    Color.Cyan)) {
                    Text(text = "Heart Sound")
                }
            }
            Spacer(modifier = Modifier.width(40.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically
            )  {
                OutlinedButton(onClick = { heading = "Spo2"
//                    data = spo2Data
                                         }, colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent, contentColor = Color.Cyan), border = BorderStroke(3.dp,
                    Color.Cyan)) {
                    Text(text = "Spo2")
                }

                OutlinedButton(onClick = { heading = "Temperature"
//                    data = temperatureData
                                         }, colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent, contentColor = Color.Cyan), border = BorderStroke(3.dp,
                    Color.Cyan)) {
                    Text(text = "Temperature")
                }
            }
        }


    }
}


@Composable
fun canvasgraph(data : List<Pair<Int,Double>>) {
    var spacing = 100f
    var graphcolor = Color.Cyan
    var transparentgraphcolor = remember { graphcolor.copy(alpha = 0.5f) }
    var uppervalue = remember { (data.maxOfOrNull { it.second }?.plus(1))?.roundToInt() ?: 0  }
    var lowervalue = remember { (data.minOfOrNull { it.second }?.toInt() ?: 0)}
    var density = LocalDensity.current
    var textpaint = remember(density){
        android.graphics.Paint().apply {
            color = android.graphics.Color.WHITE
            textAlign = android.graphics.Paint.Align.CENTER
            textSize = density.run { 12.sp.toPx() }


        }
    }
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)

            .padding(20.dp)
    )
    {

        val spaceperhour = (size.width - spacing) / data.size
        (data.indices step 2).forEach { i ->
            var hour = data[i].first
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    hour.toString(),
                    spacing + i * spaceperhour,
                    size.height,
                    textpaint
                )
            }
        }

        var pricestep = (uppervalue - lowervalue) / 5f
        (0 until data.size).forEach { i ->
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    round(lowervalue + pricestep * i).toString(),
                    30f,
                    size.height - spacing - i * size.height / data.size.toFloat(),
                    textpaint
                )
            }
        }

        var strokepath = Path().apply {
            var height = size.height
            data.indices.forEach { i ->
                var info = data[i]
                var ratio = (info.second - lowervalue) / (uppervalue - lowervalue)

                var x1 = spacing + i * spaceperhour
                var y1 = height - spacing - (ratio * height).toFloat()
                if (i == 0) moveTo(x1, y1)
                lineTo(x1, y1)
            }
        }

        drawPath(
            path = strokepath,
            color = graphcolor,
            style = Stroke(
                width = 2.dp.toPx(),
                cap = StrokeCap.Round
            )
        )

        var fillpath =
            android.graphics.Path(strokepath.asAndroidPath()).asComposePath().apply {
                lineTo(size.width - spacing, size.height - spacing)
                lineTo(spacing, size.height - spacing)
                close()
            }


        drawPath(
            path = fillpath,
            brush = Brush.verticalGradient(
                colors = listOf(
                    transparentgraphcolor,
                    Color.Transparent
                ),
                endY = size.height - spacing
            )
        )

    }
}