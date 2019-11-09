package fall_game_jam_2019.fallgamejam2019

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import kotlin.math.pow

class GameWorld(resources: Resources) {

    val planet_mass: Int =100000 // kg
    val distance_to_planet = 100000 //Meters
    val bullet_mass = 10 // Kg
    val planet_radius: Int = 10000 //meters

    var rocket: Rocket = Rocket(BitmapFactory.decodeResource(resources, R.drawable.grenade))
    var planet: Planet = Planet(BitmapFactory.decodeResource(resources, R.drawable.earth))

    fun update(delta_time: Double){
        rocket.update(delta_time)
    }

    fun draw(canvas: Canvas) {
        planet.draw(canvas)
        rocket.draw(canvas)
    }
}