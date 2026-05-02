package com.offgrid.meshchat.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [QueuedMessageEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
}
