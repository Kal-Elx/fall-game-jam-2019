package fall_game_jam_2019.fallgamejam2019

import android.content.Context
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.lang.Double.POSITIVE_INFINITY
import java.lang.Exception

class GameView(context: Context, attributes: AttributeSet) : SurfaceView(context, attributes), SurfaceHolder.Callback {
    private val thread: GameThread
    private val gameObjects = mutableListOf<GameObject>()
    private var holding: GameObject? = null

    private var touched: Boolean = false
    private var newTouch: Boolean = false
    private var touchedX: Int = 0
    private var touchedY: Int = 0


    var WORLD_HEIGHT: Int = 100 //In meters
    var WORLD_BREADTH: Int = 20 // In Meters
    var WORLD_GRAVITY: Double = 9.81 // Acceleration in m/s^2


    init {
        holder.addCallback(this)

        thread = GameThread(holder, this)
    }

    override fun surfaceDestroyed(p0: SurfaceHolder?) {
        var retry = true
        while (retry) {
            try {
                thread.setRunning(false)
                thread.join()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            retry = false
        }
    }

    override fun surfaceCreated(p0: SurfaceHolder?) {
        // game objects
        gameObjects.add(Grenade(BitmapFactory.decodeResource(resources, R.drawable.grenade)))

        // start game thread
        thread.setRunning(true)
        thread.start()
    }

    override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {

    }

    /**
     * Function to update the positions of player and game objects
     */
    fun update() {
        for (o in gameObjects) {
            o.update()
            if (holding == null && newTouch && o.touched(touchedX, touchedY)) {
                holding = o
            }
        }
        holding?.updateTouch(touchedX, touchedY)

        for (o1 in gameObjects) {
            for (o2 in gameObjects) {
                // TODO ADD Collision detection
                if (o1 != o2 && o1.hasCollided(o2)) {
                    o1.onCollision(o2)
                }
            }
        }

        for (o in gameObjects) {
            o.lateUpdate()
        }
    }

    /**
     * Everything that has to be drawn on Canvas
     */
    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        for (o in gameObjects) {
            o.draw(canvas)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // when ever there is a touch on the screen,
        // we can get the position of touch
        // which we may use it for tracking some of the game objects
        touchedX = event!!.x.toInt()
        touchedY = event.y.toInt()

        val action = event.action
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                touched = true
                newTouch = true
            }
            MotionEvent.ACTION_MOVE -> {
                touched = true
                newTouch = false
            }
            MotionEvent.ACTION_UP -> {
                touched = false
                newTouch = false
                holding?.release()
                holding = null
            }
            MotionEvent.ACTION_CANCEL -> {
                touched = false
                newTouch = false
                holding?.release()
                holding = null
            }
            MotionEvent.ACTION_OUTSIDE -> {
                touched = false
                newTouch = false
                holding?.release()
                holding = null
            }
        }
        return true
    }
}