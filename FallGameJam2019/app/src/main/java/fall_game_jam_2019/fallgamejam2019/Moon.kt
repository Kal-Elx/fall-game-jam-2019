package fall_game_jam_2019.fallgamejam2019

import kotlin.math.pow

class Moon : AstronomicalObject{
    override var x: Double = 370000000.0 // m from earth´s core
    override var y: Double = 0.0 // m from earth´s core
    override var xVel: Double = 0.0 // m/s
    override var yVel: Double = -3683/3.6 // m/s
    override val radius: Double = 1737100.0 // m
    override val mass: Double = 7342 * 10.0.pow(19) // kg
}