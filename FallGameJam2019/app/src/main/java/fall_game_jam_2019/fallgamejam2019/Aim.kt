package fall_game_jam_2019.fallgamejam2019

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.DashPathEffect


class Aim {
    protected val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    protected val screenHeight = Resources.getSystem().displayMetrics.heightPixels
    val orgX = screenWidth/2
    val orgY = screenHeight
    var x = orgX
    var y = orgY

    var resX = 0
    var resY = 0

    fun draw(canvas: Canvas) {
        val paint1 = Paint()
        paint1.setColor(Color.parseColor("#FFFFFF"))
        paint1.setStrokeWidth(10F)
        paint1.setStyle(Paint.Style.STROKE)
        paint1.setPathEffect(DashPathEffect(floatArrayOf(100f, 50f), 0f))
        paint1.setAntiAlias(true)
        paint1.setDither(true)
        canvas.drawLine(orgX.toFloat(), orgY.toFloat(), x.toFloat(), y.toFloat(), paint1)

        val paint2 = Paint()
        paint1.setColor(Color.parseColor("#FFFFFF"))
        paint1.setStrokeWidth(20F)
        paint1.setStyle(Paint.Style.STROKE)
        paint1.setAntiAlias(true)
        paint1.setDither(true)
        canvas.drawCircle(x.toFloat(), y.toFloat(),10.0F, paint1)
    }

    fun load() {
        resX = 0
        resY = 0
    }

    fun release() {
        resX = x - orgX
        resY = orgY - y
    }
}