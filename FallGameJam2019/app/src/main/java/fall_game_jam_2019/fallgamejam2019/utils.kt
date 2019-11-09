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