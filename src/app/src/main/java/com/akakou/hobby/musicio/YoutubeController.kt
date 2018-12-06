package com.akakou.hobby.musicio

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.android.youtube.player.YouTubePlayer

object YoutubeController {
    val videoList: MutableList<String> = mutableListOf()
    var youtubeActivity : YoutubeActivity? = null
    var nowPlaying = false
    var youtubePlayer :YouTubePlayer ?= null

    /**
     * Activity,YoutubePlayerをこっちのクラスに持ち込む
     */
    fun init(activity: YoutubeActivity, player: YouTubePlayer) {
        youtubeActivity = activity
        youtubePlayer = player

        youtubePlayer!!.setPlayerStateChangeListener(object : YouTubePlayer.PlayerStateChangeListener {
            /**
             * Youtubeの状態変更からイベントをとる
             */

            override fun onVideoEnded() {
                /**
                 * リスト内からひとつ取り出し再生する
                 */
                nowPlaying = false

                play()
            }

            override fun onVideoStarted() {
                nowPlaying = true
            }

            override fun onLoading() {}

            override fun onLoaded(arg0: String) {}

            override fun onError(arg0: YouTubePlayer.ErrorReason) {
                nowPlaying = false
                play()
            }

            override fun onAdStarted() {}
        })
    }

    /**
     * リストに動画を追加
     */
    fun add(element: String){
        videoList.add(element)
    }

    /**
     * 一曲リストから取り出して再生する
     */
    fun play(): Boolean {
        if (videoList.size != 0 && !nowPlaying) {
            val videoId = videoList[0]
            videoList.removeAt(0)
            youtubePlayer!!.loadVideo(videoId)
            return true
        }
        return false
    }
}