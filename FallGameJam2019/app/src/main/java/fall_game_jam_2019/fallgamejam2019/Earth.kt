package fall_game_jam_2019.fallgamejam2019

import android.graphics.Bitmap
import kotlin.math.pow

class Earth : AstronomicalObject{
    override var x: Double = 0.0 // m from earth´s core
    override var y: Double = 0.0 // m from earth´s core
    override val radius: Double = 6371000.0 // m
    override var mass: Double = 5972.0 * 10.0.pow(21) // kg
    override var xVel: Double = 0.0
    override var yVel: Double = 0.0
}