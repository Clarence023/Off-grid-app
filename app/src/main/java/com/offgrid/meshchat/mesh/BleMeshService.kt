package com.offgrid.meshchat.mesh

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.AdvertiseData
import android.bluetooth.le.AdvertiseSettings
import android.bluetooth.le.BluetoothLeAdvertiser
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.os.ParcelUuid
import java.util.UUID

class BleMeshService(context: Context) {
    private val bluetoothManager = context.getSystemService(BluetoothManager::class.java)
    private val adapter: BluetoothAdapter? = bluetoothManager?.adapter
    private val advertiser: BluetoothLeAdvertiser? = adapter?.bluetoothLeAdvertiser
    private val scanner: BluetoothLeScanner? = adapter?.bluetoothLeScanner

    fun startAdvertising(nodeId: String) {
        val settings = AdvertiseSettings.Builder()
            .setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_LOW_POWER)
            .setConnectable(false)
            .build()
        val uuid = ParcelUuid(UUID.fromString(SERVICE_UUID))
        val data = AdvertiseData.Builder()
            .addServiceUuid(uuid)
            .addServiceData(uuid, nodeId.take(8).toByteArray())
            .build()
        advertiser?.startAdvertising(settings, data, MeshAdvertiseCallback)
    }

    fun stopAdvertising() = advertiser?.stopAdvertising(MeshAdvertiseCallback)

    fun startScanning() {
        val filters = listOf(ScanFilter.Builder().setServiceUuid(ParcelUuid(UUID.fromString(SERVICE_UUID))).build())
        val settings = ScanSettings.Builder().setScanMode(ScanSettings.SCAN_MODE_LOW_POWER).build()
        scanner?.startScan(filters, settings, MeshScanCallback)
    }

    companion object {
        const val SERVICE_UUID = "7ef88e00-df6a-4d9f-89ad-cf57d7f2b07c"
    }
}
