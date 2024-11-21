package com.github.shortmikelon.univerandroidlabs.screens.sixthlab

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table1")
data class SixthLabEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String = "",
    val uid: String = ""
)

