package com.akakou.hobby.musicio

import fi.iki.elonen.NanoHTTPD
import android.content.res.Resources;



/**
 * HTTPサーバ
 */
class WebHTTPD(port: Int, response: String) : NanoHTTPD(port) {
    val youtubeController = YoutubeController;
    val responseHTML = response;
    /**
     * WEBのアクセス時のここが呼ばれる
     */
    override fun serve(session: IHTTPSession): Response {
        val videoId = session.getParms()["v"]
        if (videoId != null) {
            youtubeController.add(videoId!!)
            youtubeController.play()
        }

        return newFixedLengthResponse(responseHTML)
    }
}