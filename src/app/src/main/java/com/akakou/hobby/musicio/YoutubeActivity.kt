package com.akakou.hobby.musicio

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

import kotlinx.android.synthetic.main.activity_youtube.*

class YoutubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    private val API_TOKEN = "AIzaSyCeWrJL5RurjqZz1f-okFt0JvATeYvkPhA"
    private var videoId = "uwph0dv9E6U"
    private var youTubeView: YouTubePlayerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_youtube);

        // Youtube Viewの初期化
        youTubeView = findViewById(R.id.youtube_view) as YouTubePlayerView;
        youTubeView!!.initialize(API_TOKEN, this);
    }

    /**
     * Youtube Viewの初期化が成功した場合に呼ばれる
     */
    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean) {
        if (!wasRestored) {
            player!!.loadVideo(videoId)
        }
    }

    /**
     * Youtube Viewの初期化が成功した場合に呼ばれる
     */
    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, errorReason: YouTubeInitializationResult?) {
        val errorMessage = String.format("ERR", errorReason.toString())
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }
}

