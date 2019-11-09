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

    fun update(delta_time: Double) {

    }
}