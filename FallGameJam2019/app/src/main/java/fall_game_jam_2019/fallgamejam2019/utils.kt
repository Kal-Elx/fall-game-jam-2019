package fall_game_jam_2019.fallgamejam2019

import kotlin.math.pow
import kotlin.math.sqrt

fun distance(x1: Double,y1:Double,x2: Double,y2: Double): Double{
    return sqrt((x1-x2).pow(2)+ (y1-y2).pow(2))
}