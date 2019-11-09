package fall_game_jam_2019.fallgamejam2019

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.lang.Exception

class GameView(context: Context, attributes: AttributeSet) : SurfaceView(context, attributes), SurfaceHolder.Callback {
    private val thread: GameThread
    private val gameObjects = mutableListOf<GameObject>()

    private var touched: Boolean = false
    private var touched_x: Int = 0
    private var touched_y: Int = 0

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
        //gameObjects.add(Grenade(BitmapFactory.decodeResource(resources, R.drawable.grenade)))

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
            if (touched and o.touched(touched_x, touched_y)) {
                o.updateTouch(touched_x, touched_y)
            } else {
                o.setHeld(false)
            }
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
        touched_x = event!!.x.toInt()
        touched_y = event.y.toInt()

        val action = event.action
        when (action) {
            MotionEvent.ACTION_DOWN -> touched = true
            MotionEvent.ACTION_MOVE -> touched = true
            MotionEvent.ACTION_UP -> touched = false
            MotionEvent.ACTION_CANCEL -> touched = false
            MotionEvent.ACTION_OUTSIDE -> touched = false
        }
        return true
    }
}