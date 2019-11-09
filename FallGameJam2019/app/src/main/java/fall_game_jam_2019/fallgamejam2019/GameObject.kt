package fall_game_jam_2019.fallgamejam2019

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import java.lang.Math.pow
import kotlin.math.sqrt

enum class HitBoxType {
    CIRCLE, RECTANGLE
}

abstract class GameObject(var image: Bitmap, val mass: Double) {
    var x: Int = 0
    var y: Int = 0
    var w: Int = 0
    var h: Int = 0

    protected var xVelocity: Double = 20.0
    protected var yVelocity: Double = 20.0
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

    fun onCollision(other: GameObject){
        newXVelocity = ((this.mass-other.mass)/ (this.mass + other.mass))* this.xVelocity + ((2*other.mass)/(this.mass+other.mass))*other.xVelocity
        newYVelocity = ((this.mass-other.mass)/ (this.mass + other.mass))* this.yVelocity + ((2*other.mass)/(this.mass+other.mass))*other.yVelocity
    }

    fun touched(touchX: Int, touchY: Int): Boolean {
        val touched = sqrt((pow((x-touchX).toDouble(), 2.0)+pow((y-touchY).toDouble(),2.0))) < touchOffset
        return touched
    }

    /**
     * Draws the object on to the canvas.
     */
    fun draw(canvas: Canvas) {
        canvas.drawBitmap(image, (x-w/2).toFloat(), (y-h/2).toFloat(), null)
    }

    fun applyGravity() {
        newYVelocity += 1
    }

    fun applyAirResistance() {
        newXVelocity *= 0.99
        newYVelocity *= 0.99
    }

    fun release() {
        held = false
        holdDiffX = 0
        holdDiffY = 0
    }
}
