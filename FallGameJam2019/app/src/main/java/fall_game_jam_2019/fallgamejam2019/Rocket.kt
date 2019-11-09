package fall_game_jam_2019.fallgamejam2019

import kotlin.math.pow
import kotlin.math.sqrt

class Rocket {
    var x = 0 // m from earth´s core
    var y = 0 // m from earth´s core TODO
    var xVel = 0 // m/s
    var yVel = 0 // m/s
    val mass = Pair(433, 3) // kg, p1*10^p2

    fun launch(xVel: Int, yVel: Int) {
        this.xVel = xVel
        this.yVel = yVel
    }
}