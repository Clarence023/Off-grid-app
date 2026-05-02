package com.offgrid.meshchat.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun enqueue(message: QueuedMessageEntity)

    @Query("SELECT * FROM queued_messages WHERE deliveredAt IS NULL AND expiresAt > :now")
    suspend fun pending(now: Long = System.currentTimeMillis()): List<QueuedMessageEntity>

    @Query("UPDATE queued_messages SET deliveredAt = :deliveredAt WHERE id = :id")
    suspend fun markDelivered(id: String, deliveredAt: Long)

    @Query("DELETE FROM queued_messages WHERE expiresAt <= :now")
    suspend fun pruneExpired(now: Long = System.currentTimeMillis())
}
