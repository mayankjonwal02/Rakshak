package com.example.rakshak

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform