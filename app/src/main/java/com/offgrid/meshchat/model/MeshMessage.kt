package com.offgrid.meshchat.model

import java.util.UUID

data class MeshMessage(
    val id: String = UUID.randomUUID().toString(),
    val senderId: String,
    val recipientId: String?,
    val groupId: String?,
    val body: String,
    val hops: Int,
    val isBroadcast: Boolean,
    val createdAt: Long = System.currentTimeMillis(),
    val deliveredAt: Long? = null,
)
