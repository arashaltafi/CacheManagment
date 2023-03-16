package com.arash.altafi.cachemanagment.reactor.engine

import android.content.Context
import org.json.JSONObject
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

internal class EngineModel(
    appContext: Context,
    private val scope: String
) {

    private val path = appContext.getPath()
    private val firstDataJson = "{is:true}"

    internal fun makeDatabase(): Boolean {
        return File(path + scope).mkdir()
    }

    internal fun makeDocument(name: String): Boolean {
        val file = File("$path$scope/$name.json")
        return writeJSON(file, JSONObject(firstDataJson))
    }

    internal fun isDatabase(): Boolean {
        return File(path + scope).exists()
    }

    internal fun isDocument(name: String): Boolean {
        return File("$path$scope/$name.json").exists()
    }

    internal fun eraseAllData(): Boolean {
        return File(path + scope).deleteRecursively()
    }

    internal fun fetchJSON(name: String): JSONObject {
        val file = File("$path$scope/$name.json")
        return readJSON(file)
    }

    internal fun saveJSON(name: String, jsonObject: JSONObject): Boolean {
        val file = File("$path$scope/$name.json")
        return writeJSON(file, jsonObject)
    }

    private fun writeJSON(file: File, jsonObject: JSONObject): Boolean {
        if (jsonObject.length() <= 0)
            return false

        val fileOutputStream = FileOutputStream(file)

        fileOutputStream.write(jsonObject.toString().toByteArray())
        fileOutputStream.close()

        return true
    }

    private fun readJSON(file: File): JSONObject {
        val fileInputStream = FileInputStream(file)
        val size = fileInputStream.available()
        val buffer = ByteArray(size)

        fileInputStream.read(buffer)

        var value = String(buffer, Charsets.UTF_8)

        fileInputStream.close()

        if (value.isEmpty())
            value = firstDataJson

        return JSONObject(value)
    }
}