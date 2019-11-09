package fall_game_jam_2019.fallgamejam2019

import kotlin.math.pow
import kotlin.math.sqrt

class Rocket {
    // Logic
    var x = 0 // m from earth´s core
    var y = 0 // m from earth´s core TODO
    var xVel = 0 // m/s
    var yVel = 0 // m/s
    val mass = Pair(433, 3) // kg, p1*10^p2

    // Representation
    var screenX = 0
    var screenY = 0
    private val touchOffset = 200
    private var held = false
    private var holdDiffX = 0
    private var holdDiffY = 0

    init {
        screenX = x // TODO: Convert to pixels
        screenY = y // TODO: Convert to pixels
    }

    fun touched(touchX: Int, touchY: Int): Boolean {
        return distance(touchX.toDouble(), touchY.toDouble(), screenX.toDouble(), screenY.toDouble()) < touchOffset
    }

    fun updateTouch(touchX: Int, touchY: Int) {
        if (held) {
            screenX = (touchX) + holdDiffX
            screenY = (touchY) + holdDiffY
        } else {
            held = true
            holdDiffX = screenX - (touchX)
            holdDiffY = screenY - (touchY)
        }
    }

    fun release() {
        held = false
        holdDiffX = 0
        holdDiffY = 0

        // TODO Shout rocket
    }
}