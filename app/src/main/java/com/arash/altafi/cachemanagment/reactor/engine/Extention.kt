package com.arash.altafi.cachemanagment.reactor.engine

import android.content.Context

internal fun Context.getPath(): String {
    return this.filesDir?.path + "/"
}