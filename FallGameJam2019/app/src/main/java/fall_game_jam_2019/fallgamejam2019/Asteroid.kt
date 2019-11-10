package fall_game_jam_2019.fallgamejam2019

import kotlin.math.pow
import kotlin.math.sqrt


class Asteroid : AstronomicalObject{
    override var x: Double = getAstroX(screenWidth/2).toDouble() // m from earth´s core
    override var y: Double = getAstroY(screenHeight).toDouble() // m from earth´s core TODO
    override var xVel: Double = 0.0 // m/s
    override var yVel: Double = 0.0 // m/s
    override var mass: Double = Asteroid.mass // kg
    override val radius: Double = 1000000.0

    var affectedByGravity = false
    var launched = false
    val launchPowerFactor = 0.000005

    companion object {
        var mass = 433000.0
    }

    fun launch(xVel: Int, yVel: Int) {
        if (!launched) {
            this.xVel = xVel.toDouble() * (launchPowerFactor / scale)
            this.yVel = yVel.toDouble() * (launchPowerFactor / scale)
            affectedByGravity = true
            launched = true
        }
    }
}