package fall_game_jam_2019.fallgamejam2019

import android.app.Activity
import android.os.Bundle
import android.view.WindowManager
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



        settingsButton.setOnClickListener {
            gameView.onPause()
        }
    }
}
