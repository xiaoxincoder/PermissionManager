package com.keepshare.permission

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
class PermissionManager(@NotNull activity: FragmentActivity) {

    companion object {
        private const val TAG = "permission"
    }

    private lateinit var resultCallback: GrantResultCallback
    private var permissionFragment:PermissionFragment?

    init {
        permissionFragment = obtainPermissionFragment(activity)
    }

    private fun obtainPermissionFragment(activity: FragmentActivity): PermissionFragment? {
        var permissionFragment = findPermissionFragment(activity)
        val isNewInstance = permissionFragment == null
        if (isNewInstance) {
            permissionFragment = PermissionFragment()
            var fragmentManager = activity.supportFragmentManager
            fragmentManager.beginTransaction()
                .add(permissionFragment, TAG)
                .commitAllowingStateLoss()
            fragmentManager.executePendingTransactions()
        }
        return permissionFragment
    }


    private fun findPermissionFragment(activity: FragmentActivity): PermissionFragment? {
        return activity.supportFragmentManager.findFragmentByTag(TAG) as PermissionFragment
    }

}