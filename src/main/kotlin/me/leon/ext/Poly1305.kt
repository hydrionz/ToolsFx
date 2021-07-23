package me.leon.ext

import org.bouncycastle.crypto.BlockCipher
import org.bouncycastle.crypto.CipherParameters
import org.bouncycastle.crypto.engines.*
import org.bouncycastle.crypto.macs.Poly1305
import org.bouncycastle.crypto.params.KeyParameter
import org.bouncycastle.crypto.params.ParametersWithIV


object Poly1305Serial {
    fun getInstance(alg: String): Poly1305 {

        val blockCipher: BlockCipher = when (alg.uppercase()) {
            in arrayOf("POLY1305", "POLY1305-AES") -> AESEngine()
            "POLY1305-TWOFISH" -> TwofishEngine()
            "POLY1305-ARIA" -> ARIAEngine()
            "POLY1305-SEED" -> SEEDEngine()
            "POLY1305-SM4" -> SM4Engine()
            "POLY1305-CAMELLIA" -> CamelliaEngine()
            "POLY1305-NOEKEON" -> NoekeonEngine()
            "POLY1305-CAST6" -> CAST6Engine()
            "POLY1305-RC6" -> RC6Engine()
            "POLY1305-SERPENT" -> TnepresEngine()
            else -> throw IllegalArgumentException("illegal alg")
        }

        return Poly1305(blockCipher)
    }
}

fun Poly1305.init(key: ByteArray, iv: ByteArray) {
    val kpwiv: CipherParameters = ParametersWithIV(KeyParameter(key), iv)
    init(kpwiv)
}