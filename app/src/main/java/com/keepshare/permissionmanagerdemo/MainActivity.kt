package com.keepshare.permissionmanagerdemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.keepshare.permission.PermissionManager

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        private val permissions = listOf(android.Manifest.permission.CAMERA,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_PHONE_STATE,
            android.Manifest.permission.READ_CALENDAR
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPermission.setOnClickListener {
            requestAll()
        }
    }

    private fun request() {
        PermissionManager
            .init(this)
            .request(*permissions.toTypedArray())
            .requestCallback {

                if (it.reminderBanned) {
                    Toast.makeText(this, getText(R.string.permission_manager_reminder),
                        Toast.LENGTH_SHORT).show()
                    return@requestCallback
                }

                Toast.makeText(this, "权限申请${it.granted}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun requestAll() {
        PermissionManager
            .init(this)
            .requestEach(*permissions.toTypedArray())
            .requestCallback {
                println("权限${it.name}的获得状态是${it.granted}, 显示状态是${it.reminderBanned} ")
            }
    }
}
