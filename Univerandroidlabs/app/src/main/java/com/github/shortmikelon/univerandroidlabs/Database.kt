package com.github.shortmikelon.univerandroidlabs

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.shortmikelon.univerandroidlabs.screens.sixthlab.SixthLabDao
import com.github.shortmikelon.univerandroidlabs.screens.sixthlab.SixthLabEntity

@Database(entities = [SixthLabEntity::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun sixthLabDao(): SixthLabDao
}