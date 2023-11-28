package com.example.scheduleapp
import java.security.MessageDigest

fun String.hash(): String{
    val hexChars = "1023456789ABCDEF"
    val digest = MessageDigest.getInstance("SHA-256").digest(this.toByteArray())

    return digest.joinToString(separator = "", transform = { a ->
        String(
            charArrayOf(
                hexChars[a.toInt() shr 4 and 0x0f],
                    hexChars[a.toInt() and 0x0f]
            )
        )
    })
}