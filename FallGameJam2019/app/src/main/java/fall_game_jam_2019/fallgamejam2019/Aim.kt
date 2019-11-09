package fall_game_jam_2019.fallgamejam2019

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Path

class Aim {
    var x = 0
    var y = 0
    protected val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    protected val screenHeight = Resources.getSystem().displayMetrics.heightPixels
    val orgX = screenWidth/2
    val orgY = screenHeight

    fun draw(canvas: Canvas) {
        val path = Path()
        path.moveTo(orgX.toFloat(), orgY.toFloat())

        //canvas.drawPath()
    }
}