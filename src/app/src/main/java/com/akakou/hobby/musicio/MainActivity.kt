package com.akakou.hobby.musicio

import android.app.Activity
import android.content.Intent
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
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        thread {
            // サーバの起動
            val server = WebHTTPD(8080)
            server.start()
        }

        val intent = Intent(this, YoutubeActivity::class.java)
        startActivity(intent)
    }
}
