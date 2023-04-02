package ru.mihmas.aston_4

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.util.*
import kotlin.math.cos
import kotlin.math.sin

class ClockView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val radius = 300f
    private val numbers = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
    private var paint = Paint()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawClock(canvas)
        postInvalidateDelayed(1000)
        invalidate()
    }

    private fun drawClock(canvas: Canvas) {
        drawClockCircle(canvas)
        drawClockHands(canvas)
    }

    private fun drawClockCircle(canvas: Canvas) {
        paint.reset()
        setPaintAttrs(Color.BLACK)
        canvas.drawCircle(
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            radius,
            paint
        )
        drawMarks(canvas)
    }

    private fun drawMarks(canvas: Canvas) {
        paint.reset()
        for (number in numbers) {
            val angle = Math.PI / 6 * (number - 3)
            setPaintAttrs(Color.BLACK)
            canvas.drawLine(
                (width / 2 + cos(angle) * radius).toInt().toFloat(),
                (height / 2 + sin(angle) * radius).toInt().toFloat(),
                (width / 2 + cos(angle) * radius * 0.88).toInt().toFloat(),
                (height / 2 + sin(angle) * radius * 0.88).toInt().toFloat(),
                paint
            )
        }
    }

    private fun drawClockHands(canvas: Canvas) {
        val calendar = Calendar.getInstance()
        val hour = calendar[Calendar.HOUR].toFloat()
        drawHourHand(canvas, (hour + calendar[Calendar.MINUTE] / 60) * 5f)
        drawMinuteHand(canvas, calendar[Calendar.MINUTE].toFloat())
        drawSecondsHand(canvas, calendar[Calendar.SECOND].toFloat())
    }

    private fun drawHourHand(canvas: Canvas, loc: Float) {
        paint.reset()
        val angle = Math.PI * loc / 30 - Math.PI / 2
        val handRadius = radius - 170
        setPaintAttrs(Color.BLUE)
        canvas.drawLine(
            (width / 2 - cos(angle) * handRadius / 2).toFloat(),
            (height / 2 - sin(angle) * handRadius / 2).toFloat(),
            (width / 2 + cos(angle) * handRadius).toFloat(),
            (height / 2 + sin(angle) * handRadius).toFloat(),
            paint
        )
    }

    private fun drawMinuteHand(canvas: Canvas, loc: Float) {
        paint.reset()
        setPaintAttrs(Color.RED)
        val angle = Math.PI * loc / 30 - Math.PI / 2
        val handRadius = radius - 70
        canvas.drawLine(
            (width / 2 - cos(angle) * handRadius / 3).toFloat(),
            (height / 2 - sin(angle) * handRadius / 3).toFloat(),
            (width / 2 + cos(angle) * handRadius / 1.4).toFloat(),
            (height / 2 + sin(angle) * handRadius / 1.4).toFloat(),
            paint
        )
    }

    private fun drawSecondsHand(canvas: Canvas, loc: Float) {
        paint.reset()
        setPaintAttrs(Color.BLACK)
        val angle = Math.PI * loc / 30 - Math.PI / 2
        val handLength = radius - 100
        canvas.drawLine(
            (width / 2 - cos(angle) * handLength / 3).toFloat(),
            (height / 2 - sin(angle) * handLength / 3).toFloat(),
            (width / 2 + cos(angle) * handLength).toFloat(),
            (height / 2 + sin(angle) * handLength).toFloat(),
            paint
        )
    }

    private fun setPaintAttrs(
        color: Int,
        style: Paint.Style = Paint.Style.STROKE,
        strokeWidth: Float = 18f
    ) {
        paint.color = color
        paint.style = style
        paint.strokeWidth = strokeWidth
    }
}