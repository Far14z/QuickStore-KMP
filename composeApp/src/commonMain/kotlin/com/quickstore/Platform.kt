package com.quickstore

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform