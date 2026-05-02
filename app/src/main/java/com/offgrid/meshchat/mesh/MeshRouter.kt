package com.offgrid.meshchat.mesh

import com.offgrid.meshchat.model.DiscoveredPeer
import com.offgrid.meshchat.model.MeshMessage
import java.util.concurrent.ConcurrentHashMap

class MeshRouter {
    private val nextHopMap = ConcurrentHashMap<String, String>()

    fun updateRoute(targetNodeId: String, throughNodeId: String) {
        nextHopMap[targetNodeId] = throughNodeId
    }

    fun getNextHop(targetNodeId: String): String? = nextHopMap[targetNodeId]

    fun computeHopCount(peer: DiscoveredPeer): Int = peer.hopDistance

    fun shouldRelay(message: MeshMessage, localNodeId: String): Boolean {
        return message.recipientId != null && message.recipientId != localNodeId
    }
}
