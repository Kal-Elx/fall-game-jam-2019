package fall_game_jam_2019.fallgamejam2019

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas

/**
 * Grenade Class.
 * It could be considered as System. System is playing against you in the game.
 * Grenade is the opponent.
 */

class Grenade(var img: Bitmap) : GameObject(img, 1.0, HitBoxType.RECTANGLE) {


    init {
        x = screenWidth/2
        y = screenHeight/2
    }
}