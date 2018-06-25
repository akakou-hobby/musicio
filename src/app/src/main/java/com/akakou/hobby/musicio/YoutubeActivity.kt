package com.akakou.hobby.musicio

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView


import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener



class YoutubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    private val API_TOKEN = "AIzaSyCeWrJL5RurjqZz1f-okFt0JvATeYvkPhA"
    private var youTubeView: YouTubePlayerView? = null
    public var youtubePlayer: YouTubePlayer? = null
    public var videoList = YoutubeVideoList
    public var videoId: String ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)

        // Youtube Viewの初期化
        youTubeView = findViewById(R.id.youtube_view) as YouTubePlayerView
        youTubeView!!.initialize(API_TOKEN, this)
        videoList.add("uwph0dv9E6U")
        videoList.add("QJNANqm9QPQ")
    }
    /**
     * Youtube Viewの初期化が成功した場合に呼ばれる
     */
    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean) {
        youtubePlayer = player
        youtubePlayer!!.setPlayerStateChangeListener(object : PlayerStateChangeListener {
            /**
             * Youtubeの状態変更からイベントをとる
             */

            override fun onVideoEnded() {
                /**
                 * リスト内からひとつ取り出し再生する
                 */
                videoId = videoList.getOne()
                if (videoId != null) {
                    player!!.loadVideo(videoId)
                }
            }

            override fun onVideoStarted() {}

            override fun onLoading() {}

            override fun onLoaded(arg0: String) {}

            override fun onError(arg0: YouTubePlayer.ErrorReason) {}

            override fun onAdStarted() {}
        })

        if (!wasRestored) {
            /**
             * リスト内からひとつ取り出し再生する
             */
            videoId = videoList.getOne()
            if (videoId != null) {
                player!!.loadVideo(videoId)
            }
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
