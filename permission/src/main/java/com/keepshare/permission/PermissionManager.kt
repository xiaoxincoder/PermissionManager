package com.keepshare.permission

import android.os.Build
import android.os.Handler
import androidx.fragment.app.FragmentActivity
import org.jetbrains.annotations.NotNull

/**
 * @Author: Johnny
 * @Email keepshare@163.com
 * @CreateDate: 2020-01-05
 * @Copyright: keepshare, All rights reserved
 * @Description:
 */
class PermissionManager private constructor(@NotNull activity: FragmentActivity) {

    companion object {
        private const val TAG = "permission"
        private const val DEFAULT_ALL_NAME = "all"
        @JvmStatic
        fun init(activity: FragmentActivity):PermissionManager {
            return PermissionManager(activity)
        }
    }

    private var permissionFragment:PermissionFragment?
    private var resultCallback: ((Result) -> Unit)? = {}

    init {
        permissionFragment = obtainPermissionFragment(activity)
    }

    fun requestCallback(callback:(Result) -> Unit) {
        this.resultCallback = callback
    }

    /**
     * request all
     */
    fun request(vararg permissions:String):PermissionManager{
        val allGranted = permissions.all { isGranted(it) }
        if (allGranted) {
            Handler().post {
                resultCallback?.invoke(Result(DEFAULT_ALL_NAME, granted = true))
            }
            return this
        }

        permissionFragment?.requestPermissions(permissions) {

            val reminderBanned = it.any { permission ->
                !permission.granted && !permission.shouldShowRequestPermissionRationale
            }

            if (reminderBanned) {
                resultCallback?.invoke(Result(DEFAULT_ALL_NAME, reminderBanned = true))
                return@requestPermissions
            }

            resultCallback?.invoke(Result(DEFAULT_ALL_NAME, granted = it.all { permission -> permission.granted }))

//            resultCallback?.invoke(it.all { permission ->
//                Result(-, granted = permission.granted)
////                permission.granted
//            })
        }
        return this
    }

    /**
     * request each
     */
//    fun requestEach(vararg permissions: Array<out String>) {
//        permissions.forEach {
//
//
//            permissionFragment
//        }
//    }

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