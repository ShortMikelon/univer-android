package com.github.shortmikelon.univerandroidlabs.screens.sixthlab

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SixthLabDao {

    @Insert
    suspend fun insert(entity: SixthLabEntity): Long

    @Query("SELECT * FROM table1")
    suspend fun findAll(): List<SixthLabEntity>

    @Query("SELECT * FROM table1 WHERE id = :id")
    suspend fun findById(id: Long): SixthLabEntity

    @Update
    suspend fun update(entity: SixthLabEntity)
}

