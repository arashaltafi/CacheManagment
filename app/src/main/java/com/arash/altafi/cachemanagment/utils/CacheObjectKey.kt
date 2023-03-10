package com.arash.altafi.cachemanagment.utils

import androidx.annotation.Keep
import androidx.datastore.preferences.core.stringPreferencesKey

@Keep
data class CacheObjectKey(
    val keyString: String,
    override val option: CacheOption
) : CacheKey<String>(
    key = stringPreferencesKey(keyString),
    option = option, default = ""
) {

    suspend fun <T : CacheContract> putObject(data: T) {
        option.jsonUtils.toJson(data).apply {
            put(this)
        }
    }

    suspend inline fun <reified T : CacheContract> getObject(): T? {
        getOrNull()?.apply {
            option.jsonUtils.getSafeObject<T>(this)
                .onSuccess { return it }
                .onFailure { return null }
        }

        return null
    }
}