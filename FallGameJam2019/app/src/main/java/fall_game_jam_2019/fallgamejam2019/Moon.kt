package fall_game_jam_2019.fallgamejam2019

import kotlin.math.pow

class Moon {
    var x: Double = 370000000.0 // m from earth´s core
    var y: Double = 0.0 // m from earth´s core
    var xVel: Double = 0.0 // m/s
    var yVel: Double = -3683/3.6 // m/s
    val radius: Double = 1737100.0 // m
    val mass: Double = 7342 * 10.0.pow(19) // kg
}