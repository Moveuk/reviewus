package com.sparta.reviewus.common

import java.util.*

object Base64Util {
    fun decodeBasicAuth(headerValue: String): Array<String> {
        getEncodedAuthFromAuthorizationHeader(headerValue).let {
            Base64.getDecoder().decode(it)
        }.let {
            String(it).split(":")
        }.let {
            return arrayOf(it[0], it[1])
        }
    }

    private fun getEncodedAuthFromAuthorizationHeader(headerValue: String): String {
        return headerValue.split(" ")[1]
    }
}