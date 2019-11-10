package fall_game_jam_2019.fallgamejam2019

import kotlin.math.pow
import kotlin.math.sqrt

class Asteroid {
    var x: Double = getAstroX(screenWidth/2).toDouble() // m from earth´s core
    var y: Double = getAstroY(screenHeight).toDouble() // m from earth´s core TODO
    var xVel: Double = 0.0 // m/s
    var yVel: Double = 0.0 // m/s
    val mass: Double = 433000.0 // kg

    var affectedByGravity = false
    val launchPowerFactor = 0.00001

    fun launch(xVel: Int, yVel: Int) {
        this.xVel = xVel.toDouble()*(launchPowerFactor/scale)
        this.yVel = yVel.toDouble()*(launchPowerFactor/scale)
        affectedByGravity = true
    }
}