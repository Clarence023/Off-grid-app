package com.offgrid.meshchat.mesh

import android.content.Context
import android.net.wifi.p2p.WifiP2pManager
import android.os.Looper

class WifiDirectMeshService(context: Context) {
    private val manager = context.getSystemService(WifiP2pManager::class.java)
    private val channel = manager?.initialize(context, Looper.getMainLooper(), null)

    fun discoverPeers() {
        manager?.discoverPeers(channel, null)
    }

    fun stopDiscovery() {
        manager?.stopPeerDiscovery(channel, null)
    }
}
