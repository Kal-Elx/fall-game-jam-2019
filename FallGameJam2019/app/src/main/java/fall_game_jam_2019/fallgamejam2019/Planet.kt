package fall_game_jam_2019.fallgamejam2019

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas

class Planet(var image: Bitmap) {
    var x: Int = 0
    var y: Int = 0
    var w: Int = 0
    var h: Int = 0
    val mass = 5974000000000000000

    protected val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    protected val screenHeight = Resources.getSystem().displayMetrics.heightPixels

    init {
        w = image.width
        h = image.height
        x = image.width / 2
        y = 300
    }

    fun draw(canvas: Canvas) {
        canvas.drawBitmap(image, (x-w/2).toFloat(), (y-h/2).toFloat(), null)
    }
}