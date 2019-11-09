package fall_game_jam_2019.fallgamejam2019

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas

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

    val touchOffset = 100

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

    fun updateTouch(touch_x: Int, touch_y: Int) {
        x = touch_x - w / 2
        y = touch_y - h / 2

        xVelocity = 0.0
        yVelocity = 0.0
    }

    fun touched(touch_x: Int, touch_y: Int): Boolean {
        return touch_x >= x-touchOffset && touch_x <= (x+w+touchOffset) && touch_y >= y-touchOffset && touch_y <= (y+h+touchOffset)
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
}
