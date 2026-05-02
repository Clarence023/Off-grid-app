package com.offgrid.meshchat.model

data class DiscoveredPeer(
    val nodeId: String,
    val username: String,
    val avatarColor: Int,
    val rssi: Int,
    val hopDistance: Int,
    val lastSeen: Long = System.currentTimeMillis(),
)
