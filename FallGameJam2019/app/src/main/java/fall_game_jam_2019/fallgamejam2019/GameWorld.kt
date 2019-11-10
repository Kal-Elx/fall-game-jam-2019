package fall_game_jam_2019.fallgamejam2019

import android.content.res.Resources
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin

class GameWorld(resources: Resources) {

    var asteroid: Asteroid = Asteroid()
    var earth: Earth = Earth()
    var moon: Moon = Moon()

    val G: Double = 6.67408 * 10.0.pow(-11)
    val playbackSpeed = 100000 // Simulated seconds in one sec

    fun update(fps: Int) {
        // Move moodWS
        moon.x += moon.xVel*(playbackSpeed/fps)
        moon.y += moon.yVel*(playbackSpeed/fps)

        val earth_g = get_gravity(moon.mass, earth.mass, moon.x.pow(2) + moon.y.pow(2))
        //val earth_g_x = cos()
        //val earth_g_y = sin()

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
