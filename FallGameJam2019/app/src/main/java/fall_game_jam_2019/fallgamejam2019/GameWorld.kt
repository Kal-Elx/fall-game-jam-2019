package fall_game_jam_2019.fallgamejam2019

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas

class GameWorld(resources: Resources) {

    val planet_mass: Int =100000 // kg
    val distance_to_planet = 100000 //Meters
    val bullet_mass = 10 // Kg
    val planet_radius: Int = 10000 //meters

    var rocket: Rocket = Rocket(BitmapFactory.decodeResource(resources, R.drawable.grenade))

    fun update(delta_time: Double){

    }

    fun draw(canvas: Canvas) {
        rocket.draw(canvas)

    }


}