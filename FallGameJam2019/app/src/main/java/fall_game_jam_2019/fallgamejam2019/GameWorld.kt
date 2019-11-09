package fall_game_jam_2019.fallgamejam2019

import android.content.res.Resources
import kotlin.math.pow

class GameWorld(resources: Resources) {

    var asteroid: Asteroid = Asteroid()
    var earth: Earth = Earth()
    var moon: Moon = Moon()

    val G: Double = 6.67408 * 10.0.pow(-11)
    val playbackSpeed = 10 // Simulated seconds in one sec

    fun update(delta_time: Double) {
        
    }
}