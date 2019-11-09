package fall_game_jam_2019.fallgamejam2019

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

fun distance(x1: Double,y1:Double,x2: Double,y2: Double): Double{
    return sqrt((x1-x2).pow(2)+ (y1-y2).pow(2))
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


/*
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


fun onCollision(other: GameObject){

    newXVelocity = ((this.mass-other.mass)/ (this.mass + other.mass))* this.xVelocity + ((2*other.mass)/(this.mass+other.mass))*other.xVelocity
    newYVelocity = ((this.mass-other.mass)/ (this.mass + other.mass))* this.yVelocity + ((2*other.mass)/(this.mass+other.mass))*other.yVelocity
 }
 */

