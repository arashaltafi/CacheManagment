package com.arash.altafi.cachemanagment.utils

import androidx.annotation.Keep
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

@Keep
data class CacheOption(
    val dataStore: DataStore<Preferences>,
    val jsonUtils: JsonUtils,
)