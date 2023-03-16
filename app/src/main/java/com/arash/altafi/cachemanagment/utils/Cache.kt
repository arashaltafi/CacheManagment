package com.arash.altafi.cachemanagment.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.aaaamirabbas.reactor.handler.Reactor
import com.arash.altafi.cachemanagment.model.EnumSample
import com.arash.altafi.cachemanagment.model.User
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
    private val _sampleLongAES = "_sampleLongAES"
    private val _sampleLongBase64 = "_sampleLongBase64"
    private val _sampleEnumClassAES = "_sampleEnumClassAES"
    private val _sampleEnumClassBase64 = "_sampleEnumClassBase64"
    private val _sampleObjectAES = "_sampleObjectAES"
    private val _sampleObjectBase64 = "_sampleObjectBase64"
    private val _sampleArrayListAES = "_sampleArrayListAES"
    private val _sampleArrayListBase64 = "_sampleArrayListBase64"
    private val _sampleLongDataStore = "_sampleLongDataStore"
    private val _sampleIntDataStore = "_sampleIntDataStore"
    private val _sampleBooleanDataStore = "_sampleBooleanDataStore"
    private val _sampleStringDataStore = "_sampleStringDataStore"
    private val _sampleObjectDataStore = "_sampleObjectDataStore"

    //region Reactor

    //region string
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
    //endregion

    //region boolean
    var sampleBooleanAES: Boolean
        get() = reactorAES.get(_sampleBooleanAES, false)
        set(value) {
            reactorAES.put(_sampleBooleanAES, value)
        }

    var sampleBooleanBase64: Boolean
        get() = reactorBase64.get(_sampleBooleanBase64, false)
        set(value) {
            reactorBase64.put(_sampleBooleanBase64, value)
        }
    //endregion

    //region int
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
    //endregion

    //region Long
    var sampleLongAES: Long
        get() = reactorAES.get(_sampleLongAES, 0L)
        set(value) {
            reactorAES.put(_sampleLongAES, value)
        }

    var sampleLongBase64: Long
        get() = reactorBase64.get(_sampleLongBase64, 0L)
        set(value) {
            reactorBase64.put(_sampleLongBase64, value)
        }
    //endregion

    //region object
    var sampleEnumAESNullable: EnumSample?
        get() {
            return reactorAES.getString(_sampleEnumClassAES)?.let {
                jsonUtils.getObject<EnumSample>(it)
            }
        }
        set(value) {
            reactorAES.putString(_sampleEnumClassBase64, value?.let { jsonUtils.toJson(it) })
        }

    var sampleEnumAES: EnumSample
        get() {
            return jsonUtils.getObject(
                reactorAES.getString(
                    _sampleEnumClassAES, jsonUtils.toJson(EnumSample.Sample0)
                )
            )
        }
        set(value) {
            reactorAES.putString(_sampleEnumClassAES, jsonUtils.toJson(value))
        }

    var sampleEnumBase64: EnumSample
        get() {
            return jsonUtils.getObject(
                reactorBase64.getString(
                    _sampleEnumClassBase64, jsonUtils.toJson(EnumSample.Sample0)
                )
            )
        }
        set(value) {
            reactorBase64.putString(_sampleEnumClassBase64, jsonUtils.toJson(value))
        }

    var sampleObjectAES: User?
        get() {
            return reactorAES.getString(_sampleObjectAES)?.let {
                jsonUtils.getObject<User>(it)
            }
        }
        set(value) {
            reactorAES.putString(_sampleObjectAES, value?.let { jsonUtils.toJson(it) })
        }

    var sampleObjectBas64: User?
        get() {
            return reactorBase64.getString(_sampleObjectBase64)?.let {
                jsonUtils.getObject<User>(it)
            }
        }
        set(value) {
            reactorBase64.putString(_sampleObjectBase64, value?.let { jsonUtils.toJson(it) })
        }

    var sampleArrayListAES: ArrayList<User>?
        get() {
            return reactorAES.getString(_sampleArrayListAES)?.let {
                jsonUtils.getObjectList<User>(it)
            }?.let { ArrayList(it) }
        }
        set(value) {
            reactorAES.putString(_sampleArrayListAES, value?.let { jsonUtils.toJson(it) })
        }

    var sampleArrayListBase64: ArrayList<User>?
        get() {
            return reactorBase64.getString(_sampleArrayListBase64)?.let {
                jsonUtils.getObjectList<User>(it)
            }?.let { ArrayList(it) }
        }
        set(value) {
            reactorBase64.putString(_sampleArrayListBase64, value?.let { jsonUtils.toJson(it) })
        }
    //endregion

    //endregion

    //region DataStore
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

    val sampleBooleanDataStore = CacheKey(
        key = booleanPreferencesKey(_sampleBooleanDataStore),
        option = cacheOption, default = false
    )

    val sampleStringDataStore = CacheKey(
        key = stringPreferencesKey(_sampleStringDataStore),
        option = cacheOption, default = ""
    )

    val sampleObjectDataStore = CacheObjectKey(
        keyString = _sampleObjectDataStore, option = cacheOption
    )
    //endregion

    suspend fun eraseAllData() {
        dataStore.edit { preferences -> preferences.clear() }
        reactorAES.eraseAllData()
        reactorBase64.eraseAllData()
    }
}