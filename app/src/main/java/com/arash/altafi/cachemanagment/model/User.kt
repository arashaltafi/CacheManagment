package com.arash.altafi.cachemanagment.model

import com.aaaamirabbas.reactor.helper.ReactorContract
import com.arash.altafi.cachemanagment.utils.CacheContract
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("family")
    var family: String,
    @SerializedName("age")
    var age: Int,
): CacheContract, ReactorContract