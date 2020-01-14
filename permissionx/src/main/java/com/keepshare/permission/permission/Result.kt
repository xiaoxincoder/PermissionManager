package com.keepshare.permission

/**
 * @Author: Johnny
 * @Email keepshare@163.com
 * @CreateDate: 2020-01-12
 * @Copyright: keepshare, All rights reserved
 * @Description:
 */
data class Result(
    val name:String,
    val granted:Boolean = false,
    val reminderBanned:Boolean = false
)