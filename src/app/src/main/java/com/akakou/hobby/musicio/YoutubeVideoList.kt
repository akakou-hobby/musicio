package com.akakou.hobby.musicio

object YoutubeVideoList {
    public val videoList: MutableList<String> = mutableListOf()

    fun add(element: String){
        videoList.add(element)
    }

    fun getOne(): String? {
        if (videoList.size != 0) {
            val element = videoList[0]
            videoList.removeAt(0)
            return element
        }
        return null
    }
}