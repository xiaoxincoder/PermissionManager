package com.keepshare.permissionmanagerdemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.keepshare.permission.PermissionManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        private val permissions = listOf(android.Manifest.permission.CAMERA,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPermission.setOnClickListener {
            PermissionManager
                .init(this)
                .request(*permissions.toTypedArray())
                .requestCallback {
                    Toast.makeText(this, "权限申请$it", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
