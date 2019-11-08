package fall_game_jam_2019.fallgamejam2019

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas

/**
 * Grenade Class.
 * It could be considered as System. System is playing against you in the game.
 * Grenade is the opponent.
 */

class Grenade(var img: Bitmap) : GameObject(img) {
    /**
     * update properties for the game object
     */
    override fun update() {
        // val randomNum = ThreadLocalRandom.current().nextInt(1, 5)

        if (x > screenWidth - image.width || x < image.width) {
            xVelocity = xVelocity * -1
        }
        if (y > screenHeight - image.height || y < image.height) {
            yVelocity = yVelocity * -1
        }

        x += (xVelocity)
        y += (yVelocity)
    }

    override fun updateTouch(touch_x: Int, touch_y: Int) {
        // Do nothing
    }
}