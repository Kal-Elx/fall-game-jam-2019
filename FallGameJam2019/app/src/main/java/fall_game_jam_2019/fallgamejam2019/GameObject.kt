package fall_game_jam_2019.fallgamejam2019

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.widget.Toast

abstract class GameObject(var image: Bitmap, val mass: Double) {
    var x: Int = 0
    var y: Int = 0
    var w: Int = 0
    var h: Int = 0
    val m = mass

    protected var xVelocity: Double = 20.0
    protected var yVelocity: Double = 20.0
    protected val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    protected val screenHeight = Resources.getSystem().displayMetrics.heightPixels

    private val touchOffset = 150
    private var held = false
    private var holdDiffX = 0
    private var holdDiffY = 0

    init {
        w = image.width
        h = image.height

        x = screenWidth/2
        y = screenHeight/2
    }

    /**
     * update properties for the game object
     */
    fun update() {
        applyGravity()
        applyAirResistance()

        if (x > screenWidth - image.width || x < image.width) {
            xVelocity = xVelocity * -1
        }
        if (y > screenHeight - image.height || y < image.height) {
            yVelocity = yVelocity * -1
        }

        x += xVelocity.toInt()
        y += yVelocity.toInt()
    }

    fun updateTouch(touchX: Int, touchY: Int) {
        if (held) {
            x = (touchX - w / 2) + holdDiffX
            y = (touchY - h / 2) + holdDiffY
        } else {
            held = true
            holdDiffX = x - (touchX - w / 2)
            holdDiffY = y - (touchY - h / 2)
        }

        xVelocity = 0.0
        yVelocity = 0.0
    }

    fun touched(touchX: Int, touchY: Int): Boolean {
        val touched = touchX >= x-touchOffset && touchX <= (x+w+touchOffset) && touchY >= y-touchOffset && touchY <= (y+h+touchOffset)
        if (!touched) {
            held = false
        }
        return touched
    }

    /**
     * Draws the object on to the canvas.
     */
    fun draw(canvas: Canvas) {
        canvas.drawBitmap(image, x.toFloat(), y.toFloat(), null)
    }

    fun applyGravity() {
        yVelocity += 1
    }

    fun applyAirResistance() {
        xVelocity *= 0.99
        yVelocity *= 0.99
    }

    fun setHeld(b: Boolean) {
        held = b
    }
}
