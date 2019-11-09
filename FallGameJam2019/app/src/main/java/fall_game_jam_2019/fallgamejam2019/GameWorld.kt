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
        var moon_v2 = (moon.xVel + moon.yVel).pow(2)
        var moon_r = distance(moon.x, moon.y,earth.x,earth.y)
        var earth_g = moon.mass/ (moon_v2/moon_r)
        var moon_a_towards_earth = earth_g / moon.mass

        



    }
<<<<<<< HEAD

    fun draw(canvas: Canvas) {

    }
=======
>>>>>>> 51cf40333aea149ec1a1fad262a811bad6b4277a
}