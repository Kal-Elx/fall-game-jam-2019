package fall_game_jam_2019.fallgamejam2019

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : Activity() {

    lateinit var gameView: GameView
    lateinit var pauseView: View
    lateinit var earthMassInput: EditText
    lateinit var moonMassInput: EditText
    lateinit var asteroidMassInput: EditText
    lateinit var moonXVInput: EditText
    lateinit var moonYVInput: EditText
    lateinit var playbackSpeedInput: EditText
    lateinit var credits_button: Button

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
        credits_button = findViewById(R.id.credits_button)

        restartButton.setOnClickListener {
            gameView.restart()
        }
        credits_button.setOnClickListener{
            lateinit var dialog: AlertDialog
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Credits")
            builder.setMessage("'Earth' by Justin Nichol licensed CC-BY 3.0: https://opengameart.org/content/earth \n\n" +
                    "'The Moon' by JosipKladaric licensed CC-BY 3.0: https://opengameart.org/content/the-moon  \n\n" +
                    "'Asteroid l' by Cmdr G licensed CC-BY 3.0: https://opengameart.org/content/asteroid-l")

            builder.setNeutralButton("Close", null)
            dialog = builder.create()
            dialog.show()


        }

        settingsButton.setOnClickListener {
            if (pauseView.visibility == View.GONE){
                openSettings()
            } else{
                closeSettings()
            }
        }
    }

    fun openSettings() {
        gameView.onPause()
        pauseView.visibility = View.VISIBLE

        earthMassInput.setText((gameView.game_world.earth.mass/mass_constant).toBigDecimal().toPlainString())
        moonMassInput.setText((gameView.game_world.moon.mass/mass_constant).toBigDecimal().toPlainString())
        asteroidMassInput.setText((Asteroid.mass/mass_constant).toBigDecimal().toPlainString())
        moonXVInput.setText((gameView.game_world.moon.xVel).toBigDecimal().toPlainString())
        moonYVInput.setText((gameView.game_world.moon.yVel).toBigDecimal().toPlainString())
        playbackSpeedInput.setText((gameView.game_world.playbackSpeed).toBigDecimal().toPlainString())
    }

    fun closeSettings() {
        gameView.onUnPause()
        pauseView.visibility = View.GONE
        Asteroid.mass = asteroidMassInput.text.toString().toDouble()*mass_constant
        gameView.game_world.moon.mass= moonMassInput.text.toString().toDouble()*mass_constant
        gameView.game_world.earth.mass = earthMassInput.text.toString().toDouble()*mass_constant
        gameView.game_world.moon.xVel = moonXVInput.text.toString().toDouble()
        gameView.game_world.moon.yVel = moonYVInput.text.toString().toDouble()
        gameView.game_world.playbackSpeed = playbackSpeedInput.text.toString().toInt()
        gameView.game_world.deltaTime=gameView.game_world.playbackSpeed/gameView.game_world.fps

        val inputManager: InputMethodManager =getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(pauseView.windowToken, InputMethodManager.SHOW_FORCED)
    }

    override fun onBackPressed() {
        if (pauseView.visibility == View.VISIBLE) {
            closeSettings()
        } else {
            lateinit var dialog: AlertDialog
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Exit simulation.")
            builder.setMessage("Are you sure you want to exit the simulation?")
            val dialogClickListener = DialogInterface.OnClickListener{_,which ->
                when(which){
                    DialogInterface.BUTTON_POSITIVE -> super.onBackPressed()
                }
            }
            builder.setPositiveButton("YES",dialogClickListener)
            builder.setNegativeButton("NO",dialogClickListener)
            dialog = builder.create()
            dialog.show()
        }
    }
}
