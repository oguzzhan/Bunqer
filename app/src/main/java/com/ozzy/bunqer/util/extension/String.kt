package com.ozzy.bunqer.util.extension

import android.util.Base64
import com.ozzy.bunqer.util.KeySingleton
import java.security.KeyFactory
import java.security.Signature
import java.security.spec.PKCS8EncodedKeySpec

/**
 * Created by Oğuzhan Karacan on 31.08.2021.
 */

fun String?.signRequest(): String {
    val privateKey = KeySingleton.getInstance().private
    val base64String = Base64.encodeToString(privateKey.encoded, Base64.DEFAULT)
    return if (this.isNullOrEmpty()) {
        ""
    } else {
        signSHA256RSA(this, base64String) ?: ""
    }
}

// Create base64 encoded signature using SHA256/RSA.
private fun signSHA256RSA(input: String, strPk: String): String? {
    val b1: ByteArray = Base64.decode(strPk, Base64.DEFAULT)
    val spec = PKCS8EncodedKeySpec(b1)
    val kf: KeyFactory = KeyFactory.getInstance("RSA")
    val privateSignature = Signature.getInstance("SHA256withRSA")
    privateSignature.initSign(kf.generatePrivate(spec))
    privateSignature.update(input.toByteArray(charset("UTF-8")))
    val s = privateSignature.sign()
    return Base64.encodeToString(s, Base64.NO_WRAP)
}

