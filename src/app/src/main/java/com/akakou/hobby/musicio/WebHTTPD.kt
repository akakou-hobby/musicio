package com.akakou.hobby.musicio

import fi.iki.elonen.NanoHTTPD
import fi.iki.elonen.NanoHTTPD.Response
import java.io.IOException


/**
 * HTTPサーバ
 */
class WebHTTPD(port: Int) : NanoHTTPD(port) {
    /**
     * WEBのアクセス時のここが呼ばれる
     */
    override fun serve(session: NanoHTTPD.IHTTPSession): Response {
        return NanoHTTPD.newFixedLengthResponse("Hello")
    }

}