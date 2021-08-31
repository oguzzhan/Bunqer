package com.ozzy.bunqer.util.extension

import android.util.Base64
import com.ozzy.bunqer.util.KeySingleton
import okhttp3.RequestBody
import okio.Buffer
import okio.IOException


/**
 * Created by Oğuzhan Karacan on 30.08.2021.
 */
fun generatePublicKey(): String {

    val publicKey = KeySingleton.getInstance().public
    return "-----BEGIN PUBLIC KEY-----\n${
        Base64.encodeToString(
            publicKey.encoded,
            Base64.DEFAULT
        )
    }-----END PUBLIC KEY-----\n"
}


fun RequestBody.signedString(): String? {
    return try {
        val buffer = Buffer()
        this.writeTo(buffer)
        buffer.readUtf8().signRequest()
    } catch (e: IOException) {
        ""
    }
}