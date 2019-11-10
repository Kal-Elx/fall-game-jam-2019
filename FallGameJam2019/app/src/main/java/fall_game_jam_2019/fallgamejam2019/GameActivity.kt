package fall_game_jam_2019.fallgamejam2019

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : Activity() {

    var gameView: GameView? = null
    var pauseView: View? = null
    var earthMassInput: EditText? = null
    var moonMassInput: EditText? = null
    var asteroidMassInput: EditText? = null
    var moonXVInput: EditText? = null
    var moonYVInput: EditText? = null
    var playbackSpeedInput: EditText? = null
    val mass_constant= 100000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_game)

        gameView = findViewById(R.id.game_view) as GameView
        val restartButton = findViewById(R.id.restart_button) as ImageButton
        val settingsButton = findViewById(R.id.settings_button) as ImageButton
        pauseView = findViewById(R.id.pause_view) as View
        earthMassInput = findViewById(R.id.earth_mass) as EditText
        moonMassInput = findViewById(R.id.moon_mass) as EditText
        asteroidMassInput = findViewById(R.id.asteroid_mass) as EditText
        moonXVInput = findViewById(R.id.moon_vx) as EditText
        moonYVInput = findViewById(R.id.moon_vy) as EditText
        playbackSpeedInput = findViewById(R.id.playbackspeed)

        restartButton.setOnClickListener {
            gameView!!.restart()
        }

        settingsButton.setOnClickListener {
            if (pauseView!!.visibility == View.GONE){
                openSettings()
            } else{
                closeSettings()
            }
        }
    }

    fun openSettings() {
        gameView!!.onPause()
        pauseView!!.visibility = View.VISIBLE

        earthMassInput!!.setText((gameView!!.game_world.earth.mass/mass_constant).toBigDecimal().toPlainString())
        moonMassInput!!.setText((gameView!!.game_world.moon.mass/mass_constant).toBigDecimal().toPlainString())
        asteroidMassInput!!.setText((Asteroid.mass/mass_constant).toBigDecimal().toPlainString())
        moonXVInput!!.setText((gameView!!.game_world.moon.xVel).toBigDecimal().toPlainString())
        moonYVInput!!.setText((gameView!!.game_world.moon.yVel).toBigDecimal().toPlainString())
        playbackSpeedInput!!.setText((gameView!!.game_world.playbackSpeed).toBigDecimal().toPlainString())
    }

    fun closeSettings() {
        gameView!!.onUnPause()
        pauseView!!.visibility = View.GONE
        Asteroid.mass = asteroidMassInput!!.text.toString().toDouble()
        gameView!!.game_world.moon.mass= moonMassInput!!.text.toString().toDouble()
        gameView!!.game_world.earth.mass = earthMassInput!!.text.toString().toDouble()
        gameView!!.game_world.moon.xVel = moonXVInput!!.text.toString().toDouble()
        gameView!!.game_world.moon.yVel = moonYVInput!!.text.toString().toDouble()
        gameView!!.game_world.playbackSpeed = playbackSpeedInput!!.text.toString().toInt()
        gameView!!.game_world.deltaTime=gameView!!.game_world.playbackSpeed/gameView!!.game_world.fps
    }

    override fun onBackPressed() {
        if (pauseView!!.visibility == View.VISIBLE) {
            closeSettings()
        } else {
            super.onBackPressed()
        }
    }
}
