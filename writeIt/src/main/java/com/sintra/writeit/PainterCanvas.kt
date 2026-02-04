package com.sintra.writeit

import android.graphics.Matrix
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.zIndex
import androidx.ink.authoring.compose.InProgressStrokes
import androidx.ink.brush.Brush
import androidx.ink.brush.StockBrushes
import androidx.ink.brush.compose.createWithComposeColor
import androidx.ink.rendering.android.canvas.CanvasStrokeRenderer
import androidx.ink.strokes.Stroke

@Composable
fun PainterCanvas(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    color: Color = MaterialTheme.colorScheme.onBackground,
) {
    val canvasStrokeRenderer = remember { CanvasStrokeRenderer.create() }

    val brush = Brush.createWithComposeColor(
        family = StockBrushes.pressurePen(),
        color = color,
        size = 7F,
        epsilon = 0.1F
    )

    val eraser = Brush.createWithComposeColor(
        family = StockBrushes.pressurePen(),
        color = backgroundColor,
        size = 80F,
        epsilon = 0.5F
    )



    var drawingStrokes by remember { mutableStateOf(emptyList<Stroke>()) }

    var isErasing by remember { mutableStateOf(false) }



    Box(modifier = modifier.fillMaxSize()) {
        // The Canvas for drawing the permanent, dry strokes.
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawIntoCanvas { canvas ->
                drawingStrokes.forEach { stroke ->
                    canvasStrokeRenderer.draw(
                        stroke = stroke,
                        canvas = canvas.nativeCanvas,
                        strokeToScreenTransform = Matrix()
                    )

                }


            }
        }


        InProgressStrokes(
            defaultBrush = if (isErasing) eraser else brush,
            nextBrush = { if (isErasing) eraser else brush },
            onStrokesFinished = { finishedStrokes ->
                drawingStrokes = drawingStrokes + finishedStrokes
            },
        )


        IconButton(
            onClick = {
                isErasing = !isErasing
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .zIndex(1f)
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "erase",
                tint = if (isErasing) Color.Red else Color.White
            )
        }

    }
}