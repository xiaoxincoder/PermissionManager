package com.keepshare.permission

import androidx.annotation.Keep

/**
 * @Author: Johnny
 * @Email keepshare@163.com
 * @CreateDate: 2020-01-05
 * @Copyright: keepshare, All rights reserved
 * @Description:
 */
@Keep
data class Permission(
    val name:String,
    val granted:Boolean = false,
    val shouldShowRequestPermissionRationale:Boolean = false)