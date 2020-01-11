package com.keepshare.permission

import androidx.annotation.Keep

/**
 * @Package com.keepshare.permission
 * @Copyright: keepshare, All rights reserved(版权)
 * @Description:
 * @Author: xin
 * @Email keepshare@163.com
 * Create DateTime: 2020-01-11
 */
@Keep
data class Permission(
    val name:String,
    val granted:Boolean = false,
    val shouldShowRequestPermissionRationale:Boolean = false)