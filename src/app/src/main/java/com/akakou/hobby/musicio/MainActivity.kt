package com.akakou.hobby.musicio

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.youtube.player.*
import java.security.Provider
import com.google.android.youtube.player.YouTubePlayerView
import android.widget.Toast
import com.google.android.youtube.player.YouTubeInitializationResult
import kotlin.concurrent.thread
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener




/**
 * メインアクティビティ
 *
 * Youtubeの再生も行う
 */
class MainActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    private val API_TOKEN = "AIzaSyCeWrJL5RurjqZz1f-okFt0JvATeYvkPhA"
    private var videoId = "uwph0dv9E6U"
    private var youTubeView: YouTubePlayerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Youtube Viewの初期化
        youTubeView = findViewById(R.id.youtube_view) as YouTubePlayerView;
        youTubeView!!.initialize(API_TOKEN, this);

        thread {
            // サーバの起動
            val server = WebHTTPD(8080)
            server.start()
        }
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
