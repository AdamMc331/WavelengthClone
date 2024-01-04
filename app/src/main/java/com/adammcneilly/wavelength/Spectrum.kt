package com.adammcneilly.wavelength

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Spectrum(
    state: SpectrumState,
    modifier: Modifier = Modifier,
) {
    val pointSweepAngle = 10F

    Canvas(
        modifier = modifier,
    ) {
        drawBackground()

        drawPointMarkers(state, pointSweepAngle)

        drawGuessMarker(state)
    }
}

private fun DrawScope.drawGuessMarker(state: SpectrumState) {
    drawCircle(
        color = Color.Red,
        radius = this.size.width / 8F,
    )

    val semiCircleDegrees = 180F
    val guessMarkerDegrees = state.guessMarkerPct * semiCircleDegrees
    // Account for the fact that 0 is bottom center, we need to offset the bottom left quadrant before
    // adding our degrees.
    val guessMarkerOffset = guessMarkerDegrees + 90F

    // Using trigonometry to calculate x/y from polar coordinates of the guess marker.
    val radius = size.width / 2.5F
    val x = -(radius * sin(Math.toRadians(guessMarkerOffset.toDouble()))).toFloat() + (size.width / 2)
    val y = (radius * cos(Math.toRadians(guessMarkerOffset.toDouble()))).toFloat() + (size.height / 2)

    drawLine(
        color = Color.Red,
        start = Offset(
            x = x,
            y = y,
        ),
        end = Offset(
            x = (this.size.width / 2),
            y = (this.size.height / 2),
        ),
        strokeWidth = 25F,
        cap = StrokeCap.Round,
    )
}

private fun DrawScope.drawPointMarkers(
    state: SpectrumState,
    pointSweepAngle: Float,
) {
    val semiCircleStart = -180F
    val totalCircleDegrees = 180F
    val pointStartDegrees = (state.pointMarkerPct * totalCircleDegrees) + semiCircleStart
    val midPointStartAngle = (pointStartDegrees) - (pointSweepAngle / 2F)

    drawArc(
        color = Color.Blue,
        startAngle = midPointStartAngle,
        sweepAngle = pointSweepAngle,
        useCenter = true,
    )

    // Draw secondary point bars
    drawArc(
        color = Color.Cyan,
        startAngle = midPointStartAngle - pointSweepAngle,
        sweepAngle = pointSweepAngle,
        useCenter = true,
    )

    drawArc(
        color = Color.Cyan,
        startAngle = midPointStartAngle + pointSweepAngle,
        sweepAngle = pointSweepAngle,
        useCenter = true,
    )

    // Draw furthest point bars
    drawArc(
        color = Color.Yellow,
        startAngle = midPointStartAngle - (pointSweepAngle * 2),
        sweepAngle = pointSweepAngle,
        useCenter = true,
    )

    drawArc(
        color = Color.Yellow,
        startAngle = midPointStartAngle + (pointSweepAngle * 2),
        sweepAngle = pointSweepAngle,
        useCenter = true,
    )
}

private fun DrawScope.drawBackground() {
    drawArc(
        color = Color.White,
        startAngle = -180F,
        sweepAngle = 180F,
        useCenter = false,
    )
}

@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun UncoveredSpectrumPreview() {
    val state = SpectrumState(
        isCovered = false,
        pointMarkerPct = 0.4F,
        guessMarkerPct = 0.45F,
    )

    Spectrum(
        state = state,
        modifier = Modifier
            .size(300.dp),
    )
}
