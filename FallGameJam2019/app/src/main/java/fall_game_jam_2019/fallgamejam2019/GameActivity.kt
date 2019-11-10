package fall_game_jam_2019.fallgamejam2019

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast

class GameActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_game)

        val gameView = findViewById(R.id.game_view) as GameView
        val restartButton = findViewById(R.id.restart_button) as ImageButton
        val settingsButton = findViewById(R.id.settings_button) as ImageButton


        val pauseView = findViewById(R.id.pause_view) as View
        val eart_mass_input = findViewById(R.id.earth_mass) as EditText
        val moon_mass_input = findViewById(R.id.moon_mass) as EditText
        val asteroid_mass_input = findViewById(R.id.asteroid_mass) as EditText
        val moon_xv_input = findViewById(R.id.moon_vx) as EditText
        val moon_yv_input = findViewById(R.id.moon_vy) as EditText

        restartButton.setOnClickListener {
            gameView.restart()
        }

        settingsButton.setOnClickListener {
            val mass_constant= 100000
            if (pauseView.visibility == View.GONE){
                gameView.onPause()
                pauseView.visibility = View.VISIBLE

                eart_mass_input.setText((gameView.game_world.earth.mass/mass_constant).toBigDecimal().toPlainString())
                moon_mass_input.setText((gameView.game_world.moon.mass/mass_constant).toBigDecimal().toPlainString())
                asteroid_mass_input.setText((gameView.game_world.asteroid.mass/mass_constant).toBigDecimal().toPlainString())
                moon_xv_input.setText((gameView.game_world.moon.xVel).toBigDecimal().toPlainString())
                moon_yv_input.setText((gameView.game_world.moon.yVel).toBigDecimal().toPlainString())

            } else{
                gameView.onUnPause()
                pauseView.visibility = View.GONE
                gameView.game_world.asteroid.mass = asteroid_mass_input.text.toString().toDouble()
                gameView.game_world.moon.mass= moon_mass_input.text.toString().toDouble()
                gameView.game_world.earth.mass = eart_mass_input.text.toString().toDouble()
                gameView.game_world.moon.xVel = moon_xv_input.text.toString().toDouble()
                gameView.game_world.moon.yVel = moon_yv_input.text.toString().toDouble()

            }

        }
    }
}
