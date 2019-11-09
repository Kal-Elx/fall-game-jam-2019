package fall_game_jam_2019.fallgamejam2019

import android.graphics.Bitmap

class Player(var img: Bitmap) : GameObject(img, 2.0, HitBoxType.RECTANGLE) {

    init {
        y = screenWidth-200
    }
}