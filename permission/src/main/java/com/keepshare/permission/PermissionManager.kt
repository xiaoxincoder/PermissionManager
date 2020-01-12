package com.keepshare.permission

import android.os.Build
import android.os.Handler
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import org.jetbrains.annotations.NotNull

/**
 * @Package com.keepshare.permission
 * @Copyright: keepshare, All rights reserved(版权)
 * @Description:
 * @Author: xin
 * @Email keepshare@163.com
 * Create DateTime: 2020-01-05
 */
class PermissionManager private constructor(@NotNull activity: FragmentActivity) {

    companion object {
        private const val TAG = "permission"

        fun init(activity: FragmentActivity):PermissionManager {
            return PermissionManager(activity)
        }
    }

    private var permissionFragment:PermissionFragment?
    private var resultCallback: ((Boolean) -> Unit)? = {}

    init {
        permissionFragment = obtainPermissionFragment(activity)
    }

    fun requestCallback(callback:(Boolean) -> Unit) {
        this.resultCallback = callback
    }

    /**
     * request all
     */
    fun request(vararg permissions:String):PermissionManager{
        val allGranted = permissions.all { isGranted(it) }
        if (allGranted) {
            Handler().post {
                resultCallback?.invoke(true)
            }
            return this
        }

        permissionFragment?.requestPermissions(permissions) {

            val reminderBanned = it.any { permission ->
                !permission.granted && !permission.shouldShowRequestPermissionRationale
            }

            if (reminderBanned) {
                Toast.makeText(permissionFragment?.context,
                    permissionFragment?.getText(R.string.permission_manager_reminder),
                    Toast.LENGTH_SHORT).show()
                resultCallback?.invoke(false)
                return@requestPermissions
            }

            resultCallback?.invoke(it.all { permission ->
                permission.granted
            })
        }
        return this
    }

    /**
     * request else
     */
    fun requestEach(vararg permissions: Array<out String>) {

    }

    private fun isGranted(permission:String):Boolean{
        return !isMarshmallow() || permissionFragment!!.isGranted(permission)
    }

    private fun isRevoked(permission: String): Boolean {
        return isMarshmallow() && permissionFragment!!.isRevoked(permission)
    }

    private fun obtainPermissionFragment(activity: FragmentActivity): PermissionFragment? {
        var permissionFragment = findPermissionFragment(activity)
        val isNewInstance = permissionFragment == null
        if (isNewInstance) {
            permissionFragment = PermissionFragment()
            val fragmentManager = activity.supportFragmentManager
            fragmentManager.beginTransaction()
                .add(permissionFragment, TAG)
                .commitAllowingStateLoss()
            fragmentManager.executePendingTransactions()
        }
        return permissionFragment
    }

    private fun isMarshmallow():Boolean{
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }

    private fun findPermissionFragment(activity: FragmentActivity): PermissionFragment? {
        return activity.supportFragmentManager.findFragmentByTag(TAG) as PermissionFragment?
    }

}