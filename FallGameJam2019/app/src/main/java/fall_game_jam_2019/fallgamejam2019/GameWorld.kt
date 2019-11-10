package fall_game_jam_2019.fallgamejam2019

import android.content.res.Resources
import kotlin.math.*

class GameWorld(resources: Resources) {

    var asteroid: Asteroid = Asteroid()
    var earth: Earth = Earth()
    var moon: Moon = Moon()

    val G: Double = 6.67408 * (10.0.pow(-11))
    val fps = 50
    val playbackSpeed = 100000
    val deltaTime = playbackSpeed/fps // Simulated seconds in one sec

    fun update() {
        affectByGravity(moon, earth)
        affectByGravity(earth, moon)
        if (asteroid.affectedByGravity) {
            affectByGravity(asteroid, moon)
            affectByGravity(moon, asteroid)
            affectByGravity(asteroid, earth)
            affectByGravity(earth, asteroid)
        }

        // Move earth
        earth.x += earth.xVel * deltaTime
        earth.y += earth.yVel * deltaTime

        // Move moon
        moon.x += moon.xVel * deltaTime
        moon.y += moon.yVel * deltaTime

        // Move asteroid
        asteroid.x += asteroid.xVel * deltaTime
        asteroid.y += asteroid.yVel * deltaTime
    }

    fun affectByGravity(target: AstronomicalObject, source: AstronomicalObject) {
        val a = G*(source.mass / distance(target.x, target.y, source.x, source.y).pow(2))
        val theta = atan2(source.y-target.y, source.x-target.x)
        val ax = a * cos(theta)
        val ay = a * sin(theta)

        target.xVel += ax * (deltaTime)
        target.yVel += ay * (deltaTime)
    }
}