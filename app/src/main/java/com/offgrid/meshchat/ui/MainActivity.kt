package com.offgrid.meshchat.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.offgrid.meshchat.databinding.ActivityMainBinding
import com.offgrid.meshchat.mesh.BleMeshService
import com.offgrid.meshchat.mesh.WifiDirectMeshService

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bleMeshService: BleMeshService
    private lateinit var wifiMeshService: WifiDirectMeshService

    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { granted ->
        if (granted.values.all { it }) {
            startMesh()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bleMeshService = BleMeshService(this)
        wifiMeshService = WifiDirectMeshService(this)

        binding.scanButton.setOnClickListener { requestPermissionsAndStart() }
    }

    private fun requestPermissionsAndStart() {
        val perms = buildList {
            add(Manifest.permission.ACCESS_FINE_LOCATION)
            add(Manifest.permission.BLUETOOTH_SCAN)
            add(Manifest.permission.BLUETOOTH_CONNECT)
            add(Manifest.permission.BLUETOOTH_ADVERTISE)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                add(Manifest.permission.NEARBY_WIFI_DEVICES)
            }
        }
        if (perms.all { ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED }) {
            startMesh()
        } else permissionLauncher.launch(perms.toTypedArray())
    }

    private fun startMesh() {
        binding.statusLabel.text = "Mesh active: BLE + Wi-Fi Direct"
        bleMeshService.startAdvertising(nodeId = "local-node")
        bleMeshService.startScanning()
        wifiMeshService.discoverPeers()
    }
}
