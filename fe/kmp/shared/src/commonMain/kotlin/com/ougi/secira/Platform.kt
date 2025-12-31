package com.ougi.secira

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform