package fall_game_jam_2019.fallgamejam2019

import android.content.res.Resources
import java.security.CodeSource
import kotlin.math.*

class GameWorld(resources: Resources) {

    var asteroid: Asteroid = Asteroid()
    var earth: Earth = Earth()
    var moon: Moon = Moon()

    init {
        moon.yVel *= 10
    }

    val G: Double = 6.67408 * 10.0.pow(-11)
    val playbackSpeed = 100 // Simulated seconds in one sec
    val fps = 60

    fun update() {
        affectByGravity(moon, earth)

        // Move moon
        moon.x += moon.xVel*playbackSpeed // delta time looks weird. Use FPS instead?
        moon.y += moon.yVel*playbackSpeed

        // TODO: Gravity between earth and moon
        // TODO: Gravity between moon and asteroid

        // Move asteroid
        asteroid.x += asteroid.xVel*(playbackSpeed/fps)
        asteroid.y += asteroid.yVel*(playbackSpeed/fps)

        // TODO: Gravity between earth and asteroid
        // TODO: Gravity between asteroid and moon
    }

    fun affectByGravity(target: AstronomicalObject, source: AstronomicalObject) {
        val a = -G*(source.mass / distance(target.x, target.y, source.x, source.y).pow(2))
        val theta = atan2(target.y, target.x)
        val ax = a * cos(theta)
        val ay = a * sin(theta)

        target.xVel += ax * (playbackSpeed/fps)
        target.yVel += ay * (playbackSpeed/fps)
    }
}