package com.keepshare.permissionmanagerdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tbruyelle.rxpermissions2.RxPermissions

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permission = RxPermissions(this)
        permission.request(android.Manifest.permission.CAMERA)
    }
}
