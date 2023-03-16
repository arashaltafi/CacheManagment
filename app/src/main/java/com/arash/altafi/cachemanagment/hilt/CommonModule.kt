package com.arash.altafi.cachemanagment.hilt

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.arash.altafi.cachemanagment.reactor.handler.Reactor
import com.arash.altafi.cachemanagment.utils.Cache
import com.arash.altafi.cachemanagment.utils.JsonUtils
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun providesDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(
                SharedPreferencesMigration(
                    context, context.packageName
                )
            ),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { context.preferencesDataStoreFile(context.packageName) }
        )
    }

    @Singleton
    @Provides
    @Named("AES")
    fun provideReactorAES(context: Context) = Reactor(context, true)

    @Singleton
    @Provides
    @Named("Base64")
    fun provideReactorBase64(context: Context) = Reactor(context, false)

    @Singleton
    @Provides
    fun provideCommonCache(
        @Named("AES") reactorAES: Reactor,
        @Named("Base64") reactorBase64: Reactor,
        dataStore: DataStore<Preferences>,
        jsonUtils: JsonUtils
    ) = Cache(reactorAES, reactorBase64, dataStore, jsonUtils)

    @Singleton
    @Provides
    fun provideJsonUtils(gson: Gson) = JsonUtils(gson)

}