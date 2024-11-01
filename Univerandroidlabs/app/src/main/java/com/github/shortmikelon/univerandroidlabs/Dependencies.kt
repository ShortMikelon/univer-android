package com.github.shortmikelon.univerandroidlabs

import android.content.Context
import androidx.room.Room
import com.github.shortmikelon.univerandroidlabs.screens.sixthlab.SixthLabDao

object Dependencies {

    private lateinit var applicationContext: Context

    private val appDatabase: Database by lazy {
        Room.databaseBuilder(applicationContext, Database::class.java, "database")
            .build()
    }

    fun init(context: Context) {
        applicationContext = context
    }

    fun getDao(): SixthLabDao = appDatabase.sixthLabDao()
}