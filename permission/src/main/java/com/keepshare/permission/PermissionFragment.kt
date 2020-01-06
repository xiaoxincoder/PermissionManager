package com.keepshare.permission

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import org.jetbrains.annotations.NotNull

/**
 * @Package com.keepshare.permission
 * @Copyright: keepshare, All rights reserved(版权)
 * @Description:
 * @Author: xin
 * @Email keepshare@163.com
 * Create DateTime: 2020-01-05
 */
class PermissionFragment : Fragment() {

    companion object {
        private const val PERMISSION_REQUEST_CODE = 0x123
    }

    private lateinit var mActivity:Activity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as Activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissions(@NotNull permissions: Array<out String>){
        requestPermissions(permissions, PERMISSION_REQUEST_CODE)
    }

    /**
     * 判断是否获取了权限
     */
    @TargetApi(Build.VERSION_CODES.M)
    fun isGranted(permission:String):Boolean {
        return mActivity.checkSelfPermission(
            permission) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * 判断权限是否被取消
     */
    @TargetApi(Build.VERSION_CODES.M)
    fun isRevocked(permission: String):Boolean {
        return mActivity.packageManager
            .isPermissionRevokedByPolicy(permission, mActivity.packageName)
    }

    @TargetApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode != PERMISSION_REQUEST_CODE) return

        val shouldShowRequestPermissionRationale = Array(permissions.size) { false }

        for ((i, permission) in permissions.withIndex()) {
            shouldShowRequestPermissionRationale[i] = shouldShowRequestPermissionRationale(permission)
        }
        onResultPermissionResult(permissions, grantResults, shouldShowRequestPermissionRationale)
    }


    private fun onResultPermissionResult(permissions: Array<out String>, grantResults: IntArray,
                                 shouldShowRequestPermissionRationale:Array<out Boolean>) {
        for ((i, permission) in permissions.withIndex()) {

        }
    }

}