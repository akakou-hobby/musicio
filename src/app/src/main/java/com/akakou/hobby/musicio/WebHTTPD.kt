package com.akakou.hobby.musicio

import fi.iki.elonen.NanoHTTPD
import fi.iki.elonen.NanoHTTPD.Response
import java.io.IOException


/**
 * HTTPサーバ
 */
class WebHTTPD(port: Int) : NanoHTTPD(port) {
    val youtubeController = YoutubeController;
    /**
     * WEBのアクセス時のここが呼ばれる
     */
    override fun serve(session: NanoHTTPD.IHTTPSession): Response {
        val videoId = session.getParms()["videoId"]
        youtubeController.add(videoId!!)
        youtubeController.play()
        return NanoHTTPD.newFixedLengthResponse("ok")
    }
}