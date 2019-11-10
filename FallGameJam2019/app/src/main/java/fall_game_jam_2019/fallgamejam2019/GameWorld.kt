package fall_game_jam_2019.fallgamejam2019

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import kotlin.math.pow
import kotlin.math.sqrt

class GameWorld(resources: Resources) {

    var rocket: Rocket = Rocket()
    var earth: Earth = Earth()
    var moon: Moon = Moon()

    fun update(delta_time: Double){
        var moon_v2 = moon.xVel.pow(2) + moon.yVel.pow(2)
        var moon_r = distance(moon.x, moon.y,earth.x,earth.y)
        var moon_a_towards_earth = moon_v2/ moon_r
        var e =0




    }
}