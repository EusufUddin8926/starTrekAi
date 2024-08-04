import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomLoadingIndicator() {
    val infiniteTransition = rememberInfiniteTransition()
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(modifier = Modifier.size(50.dp)) {
        drawArc(
            color = Color.Blue,
            startAngle = 0f,
            sweepAngle = progress * 360,
            useCenter = false,
            style = Stroke(width = 8.dp.toPx())
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomLoadingIndicatorPreview() {
    CustomLoadingIndicator()
}
