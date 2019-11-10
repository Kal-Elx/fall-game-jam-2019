package fall_game_jam_2019.fallgamejam2019

import kotlin.math.pow

interface AstronomicalObject {
    var x: Double // m from earth´s core
    var y: Double // m from earth´s core
    var xVel: Double // m/s
    var yVel: Double // m/s
    val radius: Double // m
    val mass: Double // kg
}