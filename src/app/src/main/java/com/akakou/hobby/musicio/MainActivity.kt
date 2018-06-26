package com.akakou.hobby.musicio

import android.app.Activity
import android.content.Context
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
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager





    
/**
 * メインアクティビティ
 *
 */
class MainActivity : AppCompatActivity() {
    val PORT = 8080

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        thread {
            // サーバの起動
            val server = WebHTTPD(PORT)
            server.start()
        }

        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wifiInfo = wifiManager.connectionInfo
        val ipAddress = wifiInfo.ipAddress
        val ipAddressString = (ipAddress and 0xFF).toString() + "." +
                (ipAddress shr 8 and 0xFF) + "." +
                (ipAddress shr 16 and 0xFF) + "." +
                (ipAddress shr 24 and 0xFF)


        Toast.makeText(
                this,
                "Server address is : http://" + ipAddressString + ":" + PORT.toString(),
                Toast.LENGTH_LONG
        ).show()

        val intent = Intent(this, YoutubeActivity::class.java)
        startActivity(intent)

    }
}
