package fall_game_jam_2019.fallgamejam2019

import android.graphics.Bitmap

class Rocket(var img: Bitmap) : GameObject(img, 10000.0, HitBoxType.RECTANGLE) {

    var orgX: Int = 0
    var orgY: Int = 0
    var rotation: Double = 0.0

    init {
        x = screenWidth/2
        y = screenHeight-500
        orgX = x
        orgY = y
    }

    override fun release() {
        super.release()
        // Save diff between org and current to determine speed.
    }
}