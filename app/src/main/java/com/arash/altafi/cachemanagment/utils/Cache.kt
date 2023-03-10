package com.arash.altafi.cachemanagment.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.aaaamirabbas.reactor.handler.Reactor
import com.arash.altafi.cachemanagment.model.EnumSample
import com.arash.altafi.cachemanagment.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class Cache @Inject constructor(
    private val reactorAES: Reactor,
    private val reactorBase64: Reactor,
    private val dataStore: DataStore<Preferences>,
    private val jsonUtils: JsonUtils
) {

    private val _sampleStringAES = "_sampleStringAES"
    private val _sampleStringBase64 = "_sampleStringBase64"
    private val _sampleBooleanBase64 = "_sampleBooleanBase64"
    private val _sampleBooleanAES = "_sampleBooleanAES"
    private val _sampleIntAES = "_sampleIntAES"
    private val _sampleIntBase64 = "_sampleIntBase64"
    private val _sampleEnumClassBase64 = "_sampleEnumClassBase64"
    private val _sampleModel = "_sampleModel"
    private val _sampleArrayList = "_sampleArrayList"
    private val _sampleLongDataStore = "_sampleLongDataStore"
    private val _sampleIntDataStore = "_sampleIntDataStore"
    private val _sampleStringDataStore = "_sampleStringDataStore"

    var sampleStringAES: String
        get() = reactorAES.get(_sampleStringAES, "")
        set(value) {
            reactorAES.put(_sampleStringAES, value)
        }

    var sampleStringBase64: String
        get() = reactorBase64.get(_sampleStringBase64, "")
        set(value) {
            reactorBase64.put(_sampleStringBase64, value)
        }

    var sampleBooleanAES: Boolean
        get() = reactorAES.get(_sampleBooleanAES, true)
        set(value) {
            reactorAES.put(_sampleBooleanAES, value)
        }

    var sampleBooleanBase64: Boolean
        get() = reactorBase64.get(_sampleBooleanBase64, true)
        set(value) {
            reactorBase64.put(_sampleBooleanBase64, value)
        }

    var sampleIntAES: Int
        get() = reactorAES.get(_sampleIntAES, 0)
        set(value) {
            reactorAES.put(_sampleIntAES, value)
        }

    var sampleIntBase64: Int
        get() = reactorBase64.get(_sampleIntBase64, 0)
        set(value) {
            reactorBase64.put(_sampleIntBase64, value)
        }

    var sampleEnumBase64: EnumSample
        get() {
            return jsonUtils.getObject(
                reactorBase64.getString(
                    _sampleEnumClassBase64, jsonUtils.toJson(EnumSample.Sample1)
                )
            )
        }
        set(value) {
            reactorBase64.putString(_sampleEnumClassBase64, jsonUtils.toJson(value))
        }


    var sampleModel: User?
        get() {
            return reactorBase64.getString(_sampleModel)?.let {
                jsonUtils.getObject<User>(it)
            }
        }
        set(value) {
            reactorBase64.putString(_sampleModel, value?.let { jsonUtils.toJson(it) })
        }

    var sampleArrayList: ArrayList<User>?
        get() {
            return reactorBase64.getString(_sampleArrayList)?.let {
                jsonUtils.getObjectList<User>(it)
            }?.let { ArrayList(it) }
        }
        set(value) {
            reactorBase64.putString(_sampleArrayList, value?.let { jsonUtils.toJson(it) })
        }

    private val cacheOption = CacheOption(
        dataStore = dataStore, jsonUtils = jsonUtils
    )

    val sampleLongDataStore = CacheKey(
        key = longPreferencesKey(_sampleLongDataStore),
        option = cacheOption, default = 0L
    )

    val sampleIntDataStore = CacheKey(
        key = intPreferencesKey(_sampleIntDataStore),
        option = cacheOption, default = 0
    )

    val sampleStringDataStore = CacheKey(
        key = stringPreferencesKey(_sampleStringDataStore),
        option = cacheOption, default = ""
    )

    val configData = CacheObjectKey(
        keyString = "configData1", option = cacheOption
    )

    //sample putObject and getObject
    /*ioScope.launch {
        it.data?.let { data ->
            cache.configData.putObject(data)
        }
    }
    ioScope.launch {
        it.data?.let { data ->
            cache.configData.putObject(data).id
        }
    }*/

    suspend fun eraseAllData() {
        dataStore.edit { preferences -> preferences.clear() }
        reactorAES.eraseAllData()
        reactorBase64.eraseAllData()
    }
}