package fall_game_jam_2019.fallgamejam2019

import android.graphics.Bitmap

class Player(var img: Bitmap) : GameObject(img) {

    init {
        y = screenWidth-200
    }

    override fun update() {
        // Do nothing
    }

    override fun updateTouch(touch_x: Int, touch_y: Int) {
        x = touch_x - w / 2
        y = touch_y - h / 2
    }
}