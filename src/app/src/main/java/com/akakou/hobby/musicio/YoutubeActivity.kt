package com.akakou.hobby.musicio

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView


import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener
import kotlin.concurrent.thread


class YoutubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    private val API_TOKEN = "AIzaSyCeWrJL5RurjqZz1f-okFt0JvATeYvkPhA"
    private var youTubeView: YouTubePlayerView? = null
    public var youtubePlayer: YouTubePlayer? = null
    public var youtubeController = YoutubeController
    public var videoId: String ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)

        // Youtube Viewの初期化
        youTubeView = findViewById(R.id.youtube_view) as YouTubePlayerView
        youTubeView!!.initialize(API_TOKEN, this)
    }

    /**
     * Youtube Viewの初期化が成功した場合に呼ばれる
     */
    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean) {
        youtubePlayer = player
        youtubeController.init(this, youtubePlayer!!)
        if (!wasRestored) {
            /**
             * リスト内からひとつ取り出し再生する
             */
            youtubeController.play()
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
