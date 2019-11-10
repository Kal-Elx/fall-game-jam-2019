package fall_game_jam_2019.fallgamejam2019

import android.content.res.Resources
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

    fun update(fps: Int) {
        // Calculate the moon´s acceleration towards the earth.
        val Fg = -G*(earth.mass*moon.mass/moon.radius.pow(2))
        val v = atan2(moon.y, moon.x)
        val Fgx = Fg*cos(v)
        val Fgy = Fg*sin(v)
        val Ax = Fgx/moon.mass
        val Ay = Fgy/moon.mass

        moon.xVel += Ax*(playbackSpeed/fps)
        moon.yVel += Ay*(playbackSpeed/fps)

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


    fun get_gravity(m1: Double, m2: Double, r2: Double): Double{
        return G * (m1* m2)/ r2
    }
}