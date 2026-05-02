package com.offgrid.meshchat.mesh

import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult

object MeshScanCallback : ScanCallback() {
    override fun onScanResult(callbackType: Int, result: ScanResult?) {
        super.onScanResult(callbackType, result)
    }
}
