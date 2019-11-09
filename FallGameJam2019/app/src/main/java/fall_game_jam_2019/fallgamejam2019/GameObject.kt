package fall_game_jam_2019.fallgamejam2019

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas

enum class HitBoxType {
    CIRCLE, RECTANGLE
}

abstract class GameObject(var image: Bitmap, val mass: Double) {
    var x: Int = 0
    var y: Int = 0
    var w: Int = 0
    var h: Int = 0
    val m = mass


    protected var xVelocity: Double = 20.0
    protected var yVelocity: Double = 20.0
    protected var newXVelocity: Double = xVelocity
    protected var newYVelocity: Double = yVelocity

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
        newXVelocity = xVelocity
        newYVelocity = yVelocity

        applyGravity()
        applyAirResistance()

        //TODO: Remove when Collisison Detection has been implemented, Handled By
        if (x > screenWidth - image.width || x < image.width) {
            newXVelocity = xVelocity * -1
        }
        if (y > screenHeight - image.height || y < image.height) {
            newYVelocity = yVelocity * -1
        }
    }

    fun lateUpdate(){
        xVelocity = newXVelocity
        yVelocity = newYVelocity

        x += xVelocity.toInt()
        y += yVelocity.toInt()
    }

    fun updateTouch(touch_x: Int, touch_y: Int) {
        x = touch_x - w / 2
        y = touch_y - h / 2

        newXVelocity = 0.0
        newYVelocity = 0.0
    }

    fun onCollision(other: GameObject){
        newXVelocity = ((this.mass-other.mass)/ (this.mass + other.mass))* this.xVelocity + ((2*other.mass)/(this.mass+other.mass))*other.xVelocity
        newYVelocity = ((this.mass-other.mass)/ (this.mass + other.mass))* this.yVelocity + ((2*other.mass)/(this.mass+other.mass))*other.yVelocity
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
        newYVelocity += 1
    }

    fun applyAirResistance() {
        newXVelocity *= 0.99
        newYVelocity *= 0.99
    }
}
