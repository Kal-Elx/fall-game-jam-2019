package fall_game_jam_2019.fallgamejam2019

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

enum class HitBoxType {
    CIRCLE, RECTANGLE
}

abstract class GameObject(var image: Bitmap, val mass: Double, var hitBoxType: HitBoxType) {
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
    }

    /**
     * update properties for the game object
     */
    fun update(delta_time: Long) {
        newXVelocity = xVelocity
        newYVelocity = yVelocity

        applyGravity()
        applyAirResistance()

        //TODO: Remove when Collisison Detection has been implemented, Handled By it
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
        val touched = sqrt(
            (((x - touchX).toDouble()).pow(2) + (y - touchY).toDouble().pow(2))) < touchOffset
        return touched
    }

    fun hasCollided(other:GameObject): Boolean{
        if(other.hitBoxType == HitBoxType.RECTANGLE && this.hitBoxType == HitBoxType.RECTANGLE){
            var xdif = abs(this.x - other.x)
            var ydif = abs(this.y - other.y)
            if (xdif < this.w/2 + other.w/2  && ydif < this.h/2 + other.h/2 ){
                return true
            }
        }else if(other.hitBoxType == HitBoxType.CIRCLE && this.hitBoxType == HitBoxType.RECTANGLE){
            cicle_intersects_rectangle(other, this)

        }else if(other.hitBoxType == HitBoxType.RECTANGLE && this.hitBoxType == HitBoxType.CIRCLE){
            cicle_intersects_rectangle(this, other)
        }
        else if (other.hitBoxType == HitBoxType.CIRCLE && this.hitBoxType == HitBoxType.CIRCLE){
            var dx = (this.x - other.x).toDouble();
            var dy = (this.y - other.y).toDouble();
            var distance = sqrt(dx * dx + dy * dy);

            return distance < this.w + other.w
        }

        //TODO: Throw exception
        return false

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

fun cicle_intersects_rectangle(circle: GameObject, rectangle: GameObject): Boolean{
    var cdx = abs(circle.x-rectangle.x)
    var cdy = abs(circle.y-rectangle.y)

    //The case when the circle center is inside of the rectangle + the radius, there is a possibility of a collision
    if (cdx > (rectangle.w/2 + circle.w/2) || (cdy > (rectangle.h/2 + circle.h/2))) {
        return false
    }

    // The Circles center is inside of the rectangle
    if(cdx <= (rectangle.w/2) || (cdy <= (rectangle.h/2))){
        return true
    }

    // Handling the corner
    var cdsq = (cdx - rectangle.w/2).toDouble().pow(2) + (cdy-rectangle.h/2).toDouble().pow(2)
    return cdsq <= ((circle.w/2).toDouble().pow(2))

}
