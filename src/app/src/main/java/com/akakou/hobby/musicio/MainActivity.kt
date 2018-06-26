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
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.Charset


/**
 * メインアクティビティ
 *
 */
class MainActivity : AppCompatActivity() {
    val PORT = 8080

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        var response = ""
        val res = resources
        val inputStream = res.openRawResource(R.raw.index)


        val reader = BufferedReader(InputStreamReader(inputStream, Charset.forName("UTF-8")))


        while(true){
            val line = reader.readLine()

            if(line == null) break

            response += line
        }

        thread {
            // サーバの起動
            val server = WebHTTPD(PORT, response)
            server.start()
        }

        val intent = Intent(this, YoutubeActivity::class.java)
        startActivity(intent)

    }
}
