package fall_game_jam_2019.fallgamejam2019

import android.content.res.Resources
import kotlin.math.*

class GameWorld(resources: Resources) {

    var earth: Earth = Earth()
    var moon: Moon = Moon()
    val asteroids = mutableListOf<Asteroid>()

    val G: Double = 6.67408 * (10.0.pow(-11))
    val fps = 50
    val playbackSpeed = 100000
    val deltaTime = playbackSpeed/fps // Simulated seconds in one sec

    fun update() {
        // Affect objects by gravity
        affectByGravity(moon, earth)
        affectByGravity(earth, moon)
        for (asteroid in asteroids) {
            affectByGravity(asteroid, moon)
            affectByGravity(moon, asteroid)
            affectByGravity(asteroid, earth)
            affectByGravity(earth, asteroid)
        }
        for (i in 0..asteroids.size-1) {
            for (j in i+1..asteroids.size-1) {
                affectByGravity(asteroids[i], asteroids[j])
                affectByGravity(asteroids[j], asteroids[i])
            }
        }

        // Move earth
        earth.x += earth.xVel * deltaTime
        earth.y += earth.yVel * deltaTime

        // Move moon
        moon.x += moon.xVel * deltaTime
        moon.y += moon.yVel * deltaTime

        // Move asteroids
        for (asteroid in asteroids) {
            asteroid.x += asteroid.xVel * deltaTime
            asteroid.y += asteroid.yVel * deltaTime
        }
    }

    fun launchAsteroid(xVel: Int, yVel: Int) {
        val asteroid = Asteroid()
        asteroid.launch(xVel, yVel)
        asteroids.add(asteroid)
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