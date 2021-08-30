package com.ozzy.bunqer.util.extension

import android.util.Base64
import java.security.KeyPairGenerator

/**
 * Created by Oğuzhan Karacan on 30.08.2021.
 */
fun Any.generatePublicKey(): String {
    val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
    keyPairGenerator.initialize(2048)
    val publicKey = keyPairGenerator.genKeyPair().public
    return "-----BEGIN PUBLIC KEY-----\n${
        Base64.encodeToString(
            publicKey.encoded,
            Base64.DEFAULT
        )
    }-----END PUBLIC KEY-----\n"
}