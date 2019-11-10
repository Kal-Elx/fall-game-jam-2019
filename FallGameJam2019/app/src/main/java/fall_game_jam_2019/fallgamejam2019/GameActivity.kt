package fall_game_jam_2019.fallgamejam2019

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_game)

        val gameView = findViewById(R.id.game_view) as GameView
        val restartButton = findViewById(R.id.restart_button) as ImageButton
        val settingsButton = findViewById(R.id.settings_button) as ImageButton


        val pauseView = findViewById(R.id.pause_view) as View
        val earthMassInput = findViewById(R.id.earth_mass) as EditText
        val moonMassInput = findViewById(R.id.moon_mass) as EditText
        val asteroidMassInput = findViewById(R.id.asteroid_mass) as EditText
        val moonXVInput = findViewById(R.id.moon_vx) as EditText
        val moonYVInput = findViewById(R.id.moon_vy) as EditText

        restartButton.setOnClickListener {
            gameView.restart()
        }

        settingsButton.setOnClickListener {
            val mass_constant= 100000
            if (pauseView.visibility == View.GONE){
                gameView.onPause()
                pauseView.visibility = View.VISIBLE

                earthMassInput.setText((gameView.game_world.earth.mass/mass_constant).toBigDecimal().toPlainString())
                moonMassInput.setText((gameView.game_world.moon.mass/mass_constant).toBigDecimal().toPlainString())
                asteroidMassInput.setText((Asteroid.mass/mass_constant).toBigDecimal().toPlainString())
                moonXVInput.setText((gameView.game_world.moon.xVel).toBigDecimal().toPlainString())
                moonYVInput.setText((gameView.game_world.moon.yVel).toBigDecimal().toPlainString())

            } else{
                gameView.onUnPause()
                pauseView.visibility = View.GONE
                Asteroid.mass = asteroidMassInput.text.toString().toDouble()
                gameView.game_world.moon.mass= moonMassInput.text.toString().toDouble()
                gameView.game_world.earth.mass = earthMassInput.text.toString().toDouble()
                gameView.game_world.moon.xVel = moonXVInput.text.toString().toDouble()
                gameView.game_world.moon.yVel = moonYVInput.text.toString().toDouble()

            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }
}
