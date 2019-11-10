package fall_game_jam_2019.fallgamejam2019

import android.content.res.Resources
import kotlin.math.pow

class GameWorld(resources: Resources) {

    var asteroid: Asteroid = Asteroid()
    var earth: Earth = Earth()
    var moon: Moon = Moon()

    val G: Double = 6.67408 * 10.0.pow(-11)
    val playbackSpeed = 1000 // Simulated seconds in one sec

    fun update(delta_time: Double) {
        // Move moon
        moon.x += moon.xVel*playbackSpeed // delta time looks weird. Use FPS instead?
        moon.y += moon.yVel*playbackSpeed

        // TODO: Gravity between earth and moon
        // TODO: Gravity between moon and asteroid

        // Move asteroid
        asteroid.x += asteroid.xVel*playbackSpeed
        asteroid.y += asteroid.yVel*playbackSpeed

        // TODO: Gravity between earth and asteroid
        // TODO: Gravity between asteroid and moon
    }
}