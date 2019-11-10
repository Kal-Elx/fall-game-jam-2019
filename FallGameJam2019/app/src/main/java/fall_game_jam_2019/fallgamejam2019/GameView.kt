package fall_game_jam_2019.fallgamejam2019

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.lang.Exception

class GameView(context: Context, attributes: AttributeSet) : SurfaceView(context, attributes), SurfaceHolder.Callback {
    private val thread: GameThread

    private var touched: Boolean = false
    private var touchedX: Int = 0
    private var touchedY: Int = 0
    private var paused: Boolean = false

    private var game_world: GameWorld = GameWorld(resources)
    private var aim = Aim()

    private var bitmap_earth: Bitmap = Bitmap.createScaledBitmap(
        BitmapFactory.decodeResource(resources, R.drawable.earth2), 300, 300, false)
    private var bitmap_moon: Bitmap = Bitmap.createScaledBitmap(
        BitmapFactory.decodeResource(resources, R.drawable.moon), 81, 81, false)
    private var bitmap_asteroid: Bitmap = Bitmap.createScaledBitmap(
        BitmapFactory.decodeResource(resources, R.drawable.asteroid), 54, 54, false)

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
        // start game thread
        thread.setRunning(true)
        thread.start()
    }

    override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {}

    /**
     * Function to update the positions of player and game objects
     */
    fun update() {
        if (!paused) {
            if (touched) {
                aim.x = touchedX
                aim.y = touchedY
            }
            game_world?.update()
        }
    }

    /**
     * Everything that has to be drawn on Canvas
     */
    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        if (touched && !game_world.asteroid.launched) {
            aim.draw(canvas)
        }
        drawGameWorld(canvas)
    }

    private fun drawGameWorld(canvas: Canvas) {
        // Draw Earth
        //canvas.drawBitmap(bitmap_earth, ((screenWidth-bitmap_earth.width)/2).toFloat(), ((screenWidth-bitmap_earth.width)/2).toFloat(), null)
        canvas.drawBitmap(bitmap_earth, getPixelX(game_world.earth.x) - ((bitmap_earth.width)/2).toFloat(), getPixelY(game_world.earth.y) - ((bitmap_earth.height)/2).toFloat(), null)

        // Draw Moon
        canvas.drawBitmap(bitmap_moon, getPixelX(game_world.moon.x) - ((bitmap_moon.width)/2).toFloat(), getPixelY(game_world.moon.y) - ((bitmap_moon.height)/2).toFloat(), null)

        // Draw Asteroid
        canvas.drawBitmap(bitmap_asteroid, getPixelX(game_world.asteroid.x) - ((bitmap_asteroid.width)/2).toFloat(), getPixelY(game_world.asteroid.y) - ((bitmap_asteroid.height)/2).toFloat(), null)
    }

    fun restart() {
        game_world = GameWorld(resources)
        aim.load()
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
                aim.load()
            }
            MotionEvent.ACTION_MOVE -> {
                touched = true
            }
            MotionEvent.ACTION_UP -> {
                touched = false
                aim.release()
                game_world.asteroid.launch(aim.resX, aim.resY)
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

    fun onPause(){
        paused = true

    }

    fun onUnPause(){
        paused =false
    }
}