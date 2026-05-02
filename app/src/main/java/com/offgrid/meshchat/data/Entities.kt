package com.offgrid.meshchat.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "queued_messages")
data class QueuedMessageEntity(
    @PrimaryKey val id: String,
    val senderId: String,
    val recipientId: String?,
    val groupId: String?,
    val payload: String,
    val isBroadcast: Boolean,
    val hops: Int,
    val createdAt: Long,
    val expiresAt: Long,
    val deliveredAt: Long? = null,
)
