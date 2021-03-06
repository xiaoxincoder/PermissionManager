package com.keepshare.permission

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import org.jetbrains.annotations.NotNull

/**
 * @Author: Johnny
 * @Email keepshare@163.com
 * @CreateDate: 2020-01-05
 * @Copyright: keepshare, All rights reserved
 * @Description:
 */
class PermissionFragment : Fragment() {

    companion object {
        private const val PERMISSION_REQUEST_CODE = 0x123
    }

    private lateinit var mActivity:Activity
    private var resultCallback: ((List<Permission>) -> Unit)? = {}

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as Activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissions(@NotNull permissions: Array<out String>, resultCallback: ((List<Permission>) -> Unit)? = {}){
        this.resultCallback = resultCallback
        requestPermissions(permissions, PERMISSION_REQUEST_CODE)
    }


    @TargetApi(Build.VERSION_CODES.M)
    fun isGranted(permission:String):Boolean {
        return mActivity.checkSelfPermission(
            permission) == PackageManager.PERMISSION_GRANTED
    }


    @TargetApi(Build.VERSION_CODES.M)
    fun isRevoked(permission: String):Boolean {
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

        val resultList = mutableListOf<Permission>()
        permissions.forEachIndexed { index, permission ->
            val permission = Permission(permission,
                grantResults[index] == PackageManager.PERMISSION_GRANTED,
                shouldShowRequestPermissionRationale[index])
            resultList.add(permission)
        }
        resultCallback?.invoke(resultList)

    }

}