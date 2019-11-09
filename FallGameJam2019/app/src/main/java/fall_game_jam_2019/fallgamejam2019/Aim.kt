package fall_game_jam_2019.fallgamejam2019

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.DashPathEffect



class Aim {
    var x = 0
    var y = 0
    protected val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    protected val screenHeight = Resources.getSystem().displayMetrics.heightPixels
    val orgX = screenWidth/2
    val orgY = screenHeight

    var resX = 0
    var resY = 0

    fun draw(canvas: Canvas) {
        val paint = Paint()
        paint.setColor(Color.parseColor("#FFFFFF"))
        paint.setStrokeWidth(10F)
        paint.setStyle(Paint.Style.STROKE)
        paint.setPathEffect(DashPathEffect(floatArrayOf(100f, 50f), 0f))
        paint.setAntiAlias(true)
        paint.setDither(true)
        canvas.drawLine(orgX.toFloat(), orgY.toFloat(), x.toFloat(), y.toFloat(), paint)
    }

    fun load() {
        resX = 0
        resY = 0
    }

    fun release() {
        resX = orgX - x
        resY = orgY - y
    }
}