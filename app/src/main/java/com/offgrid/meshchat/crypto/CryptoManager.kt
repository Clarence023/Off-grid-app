package com.offgrid.meshchat.crypto

import com.goterl.lazysodium.LazySodiumAndroid
import com.goterl.lazysodium.SodiumAndroid
import java.security.SecureRandom

class CryptoManager {
    private val sodium = LazySodiumAndroid(SodiumAndroid())
    private val random = SecureRandom()

    fun createIdentitySeed(): ByteArray = ByteArray(32).also(random::nextBytes)

    fun performCurve25519Handshake(localPrivate: ByteArray, remotePublic: ByteArray): ByteArray {
        val shared = ByteArray(32)
        sodium.cryptoScalarMult(shared, localPrivate, remotePublic)
        return shared
    }
}
