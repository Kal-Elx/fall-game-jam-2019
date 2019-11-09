package fall_game_jam_2019.fallgamejam2019

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import java.math.BigInteger
import kotlin.math.pow

class GameWorld(resources: Resources) {

    var rocket: Rocket = Rocket()
    var earth: Earth = Earth()
    var moon: Moon = Moon()

    fun update(delta_time: Double){
        var moon_tot_v = (moon.xVel + moon.yVel).pow(2)
        var moon_tot_dist = distance(moon.x.toDouble(), moon.y.toDouble(),earth.x.toDouble(),earth.y.toDouble() )
        var moon_f_to_earth = (moon.mass.divide(BigInteger((moon_tot_v/moon_tot_dist).toString())))
        var moon_a_to_earth = (moon_f_to_earth / moon.mass)


    }

    fun draw(canvas: Canvas) {

    }
}