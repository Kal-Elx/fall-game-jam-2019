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

    private var touched: Boolean = false
    private var touchedX: Int = 0
    private var touchedY: Int = 0

    private var game_world: GameWorld?= null

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

        game_world = GameWorld(resources)

        // start game thread
        thread.setRunning(true)
        thread.start()
    }

    override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {
    }

    /**
     * Function to update the positions of player and game objects
     */
    fun update(delta_time: Double) {
        if (touched) {
            // Draw aim
        }
        game_world?.update(delta_time)
    }

    /**
     * Everything that has to be drawn on Canvas
     */
    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        game_world?.draw(canvas)
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
            }
            MotionEvent.ACTION_MOVE -> {
                touched = true
            }
            MotionEvent.ACTION_UP -> {
                touched = false
            }
            MotionEvent.ACTION_CANCEL -> {
                touched = false
            }
            MotionEvent.ACTION_OUTSIDE -> {
                touched = false
            }
        }
        return true
    }
}