package com.arash.altafi.cachemanagment.utils

import androidx.annotation.Keep
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.*
import java.io.IOException

@Keep
open class CacheKey<T>(
    open val key: Preferences.Key<T>,
    open val option: CacheOption,
    open val default: T
) {
    suspend fun put(data: T) {
        option.dataStore.edit { it[key] = data }
    }

    suspend fun remove(key: Preferences.Key<T>) {
        option.dataStore.edit { it.remove(key) }
    }

    suspend fun get(): T {
        return option.dataStore.getSubscribe().map {
            it[key] ?: default
        }.first()
    }

    suspend fun get(default: T): T {
        return option.dataStore.getSubscribe().map {
            it[key] ?: default
        }.first()
    }

    suspend fun getOrNull(): T? {
        return option.dataStore.getSubscribe().map {
            it[key]
        }.firstOrNull()
    }

    fun DataStore<Preferences>.getSubscribe(): Flow<Preferences> {
        return data.catch {
            if (it is IOException) {
                emit(emptyPreferences())
            } else throw it
        }
    }
}