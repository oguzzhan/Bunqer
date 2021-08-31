package com.ozzy.bunqer.util

import java.security.KeyPair
import java.security.KeyPairGenerator

/**
 * Created by Oğuzhan Karacan on 31.08.2021.
 */
object KeySingleton {

    private var keyPair: KeyPair? = null

    fun getInstance(): KeyPair {
        if (keyPair == null) {
            val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
            keyPairGenerator.initialize(2048)
            keyPair = keyPairGenerator.genKeyPair()
        }
        return keyPair!!
    }
}