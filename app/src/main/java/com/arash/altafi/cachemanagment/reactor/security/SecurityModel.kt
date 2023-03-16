package com.arash.altafi.cachemanagment.reactor.security

import android.content.Context
import com.arash.altafi.cachemanagment.reactor.helper.AESUtils
import com.arash.altafi.cachemanagment.reactor.helper.Base64Utils
import com.arash.altafi.cachemanagment.reactor.helper.HashUtils


internal class SecurityModel(
    private val appContext: Context,
    private val isEncrypt: Boolean
) {

    private val password = getPassword()

    internal fun encryptValue(value: String): String {
        return if (isEncrypt) AESUtils.encrypt(password, value)
        else Base64Utils.encode(value)
    }

    internal fun decryptValue(value: String): String {
        return if (isEncrypt) AESUtils.decrypt(password, value)
        else Base64Utils.decode(value)
    }

    private fun getPassword(): String {
        val uuid = (HashUtils.getSign(appContext) + HashUtils.getUUID(appContext))
        return HashUtils.getSHA256(uuid) ?: uuid
    }
}