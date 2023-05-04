package com.arash.altafi.cachemanagment.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesUtils {

    private const val CLASS_NAME = "my_name_class"
    private const val STRING_KEY = "name_class"

    private var preferences: SharedPreferences? = null

    fun sharedPrefName(context: Context) {
        preferences = context.getSharedPreferences(CLASS_NAME, Context.MODE_PRIVATE)
    }

    fun save(name: String) {
        val editor: SharedPreferences.Editor = preferences!!.edit()
        editor.putString(STRING_KEY, name)
        editor.apply()
    }

    fun get(): String? {
        return preferences!!.getString(STRING_KEY, null)
    }

    fun clear() {
        val editor: SharedPreferences.Editor = preferences!!.edit()
        editor.clear()
        editor.apply()
    }

    fun remove() {
        val editor: SharedPreferences.Editor = preferences!!.edit()
        editor.remove(STRING_KEY)
        editor.apply()
    }

}