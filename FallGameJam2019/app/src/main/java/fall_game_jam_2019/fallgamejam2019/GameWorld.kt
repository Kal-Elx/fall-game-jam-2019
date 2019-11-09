package fall_game_jam_2019.fallgamejam2019

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import kotlin.math.pow

class GameWorld(resources: Resources) {

    var rocket: Rocket = Rocket()
    var earth: Earth = Earth()
    var moon: Moon = Moon()

    val G: Double = 6.67408 * 10.0.pow(-11)

    fun update(delta_time: Double){

        var moon_v2 = (moon.xVel.pow(2) + moon.yVel.pow(2))
        var moon_r = distance(moon.x, moon.y,earth.x,earth.y)
        var earth_g = moon.mass/ (moon_v2/moon_r)
        var moon_a_towards_earth = earth_g / moon.mass

        val a = G*(earth.mass/distance(moon.x, moon.y,earth.x,earth.y).pow(2))
    }
}