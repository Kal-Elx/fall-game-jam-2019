package fall_game_jam_2019.fallgamejam2019

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.lang.Double.POSITIVE_INFINITY
import java.lang.Exception
import java.lang.Math.sqrt

class GameView(context: Context, attributes: AttributeSet) : SurfaceView(context, attributes), SurfaceHolder.Callback {
    private val thread: GameThread

    private var touched: Boolean = false
    private var touchedX: Int = 0
    private var touchedY: Int = 0

    private var game_world: GameWorld = GameWorld(resources)
    private var aim = Aim()

    private var bitmap_earth: Bitmap = Bitmap.createScaledBitmap(
        BitmapFactory.decodeResource(resources, R.drawable.earth2), 300, 300, false)
    private var bitmap_moon: Bitmap = Bitmap.createScaledBitmap(
        BitmapFactory.decodeResource(resources, R.drawable.moon), 81, 81, false)
    //private var bitmap_rocket: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.moon)

    protected val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    protected val screenHeight = Resources.getSystem().displayMetrics.heightPixels

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

    override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {
    }

    /**
     * Function to update the positions of player and game objects
     */
    fun update(delta_time: Double) {
        if (touched) {
<<<<<<< HEAD
=======
            aim.x = touchedX
            aim.y = touchedY
>>>>>>> 51cf40333aea149ec1a1fad262a811bad6b4277a
        }
        game_world?.update(delta_time)
    }

    /**
     * Everything that has to be drawn on Canvas
     */
    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        draw_game_world(canvas)
        if (touched) {
            aim.draw(canvas)
        }
    }

    private fun draw_game_world(canvas: Canvas) {
        // Draw Earth
        canvas.drawBitmap(bitmap_earth, ((screenWidth-bitmap_earth.width)/2).toFloat(), ((screenWidth-bitmap_earth.width)/2).toFloat(), null)

        // Draw Moon
        canvas.drawBitmap(bitmap_moon, getPixelCoordinate(game_world.moon.x) - ((bitmap_moon.width)/2).toFloat(), getPixelCoordinate(game_world.moon.y) - ((bitmap_moon.height)/2).toFloat(), null)
    }

    private fun getPixelCoordinate (astro_coord: Int): Float {
        return astro_coord.toFloat() * (500f / 370000000f) + (screenWidth)/2
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