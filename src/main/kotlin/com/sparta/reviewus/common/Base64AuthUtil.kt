package com.sparta.reviewus.common

import java.util.*

object Base64AuthUtil {
    fun decodeBasicAuth(basicAuth: String): Array<String> {
        basicAuth.split(" ")[1].let {
            Base64.getDecoder().decode(it)
        }.let { String(it).split(":")
        }.let {
            return arrayOf(it[0], it[1])
        }
    }
}