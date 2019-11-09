package fall_game_jam_2019.fallgamejam2019

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

abstract class GameObject(var image: Bitmap, val mass: Double) {
    var x: Int = 0
    var y: Int = 0
    var w: Int = 0
    var h: Int = 0

    protected var xVelocity: Double = 0.0
    protected var yVelocity: Double = 0.0
    protected var newXVelocity: Double = xVelocity
    protected var newYVelocity: Double = yVelocity

    protected val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    protected val screenHeight = Resources.getSystem().displayMetrics.heightPixels

    private val touchOffset = 200
    private var held = false
    private var holdDiffX = 0
    private var holdDiffY = 0

    init {
        w = image.width
        h = image.height
    }

    /**
     * update properties for the game object
     */
    fun update(delta_time: Double) {
        newXVelocity = xVelocity
        newYVelocity = yVelocity

        //TODO: Remove when Collisison Detection has been implemented, Handled By it
        if (x > screenWidth - image.width || x < image.width) {
            newXVelocity = xVelocity * -1
        }
        if (y > screenHeight - image.height || y < image.height) {
            newYVelocity = yVelocity * -1
        }
    }

    fun lateUpdate(delta_time: Double){
        xVelocity = newXVelocity
        yVelocity = newYVelocity

        x += xVelocity.toInt()
        y += yVelocity.toInt()
    }

    fun updateTouch(touchX: Int, touchY: Int) {
        if (held) {
            x = (touchX) + holdDiffX
            y = (touchY) + holdDiffY
        } else {
            held = true
            holdDiffX = x - (touchX)
            holdDiffY = y - (touchY)
        }

        newXVelocity = 0.0
        newYVelocity = 0.0
    }


    fun touched(touchX: Int, touchY: Int): Boolean {
        val touched = sqrt(
            (((x - touchX).toDouble()).pow(2) + (y - touchY).toDouble().pow(2))) < touchOffset
        return touched
    }



    /**
     * Draws the object on to the canvas.
     */
    fun draw(canvas: Canvas) {
        canvas.drawBitmap(image, (x-w/2).toFloat(), (y-h/2).toFloat(), null)
    }

    open fun release() {
        held = false
        holdDiffX = 0
        holdDiffY = 0
    }
}
