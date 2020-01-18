## PermissionManager

### 使用方法

1.添加依赖<br> 
~~~~
implementation 'com.keepshare.permission:permission:0.0.1-alpha'
~~~~ 
2.同时回调<br>  
~~~~
val permissions = listof(android.Manifest.permission.CAMERA, android.Manifest.permission.READ_EXTERNAL_STORAGE)

PermissionManager.init(activity)
       .request(*permission.toTypedArray())
       .requestCallback {
            println("申请权限是否成功${it.granted}")
       }
~~~~  
3.单个回调
~~~
val permissions = listof(android.Manifest.permission.CAMERA, android.Manifest.permission.READ_EXTERNAL_STORAGE)

PermissionManager.init(activity)
       .requestEach(*permission.toTypedArray())
       .requestCallback {
            
       }
~~~
4.Result 解释
~~~
name: 权限名称 (同时回调时 name 为 'all')
granted: 权限是否获取成功
reminderBanned: 是否点击了 '拒绝后不在提示'
~~~
### android 中需要动态申请的权限
~~~~
1.group:android.permission-group.CONTACTS
        permission:android.permission.WRITE_CONTACTS 
        permission:android.permission.GET_ACCOUNTS 
        permission:android.permission.READ_CONTACTS
        
2.group:android.permission-group.PHONE
        permission:android.permission.READ_CALL_LOG 
        permission:android.permission.READ_PHONE_STATE 
        permission:android.permission.CALL_PHONE 
        permission:android.permission.WRITE_CALL_LOG 
        permission:android.permission.USE_SIP 
        permission:android.permission.PROCESS_OUTGOING_CALLS 
        permission:com.android.voicemail.permission.ADD_VOICEMAIL
        
3.group:android.permission-group.CALENDAR
        permission:android.permission.READ_CALENDAR 
        permission:android.permission.WRITE_CALENDAR  
            
4.group:android.permission-group.CAMERA
        permission:android.permission.CAMERA
        
5.group:android.permission-group.SENSORS
        permission:android.permission.BODY_SENSORS
        
6.group:android.permission-group.LOCATION
        permission:android.permission.ACCESS_FINE_LOCATION 
        permission:android.permission.ACCESS_COARSE_LOCATION
        
7.group:android.permission-group.STORAGE
        permission:android.permission.READ_EXTERNAL_STORAGE 
        permission:android.permission.WRITE_EXTERNAL_STORAGE
        
8.group:android.permission-group.MICROPHONE
        permission:android.permission.RECORD_AUDIO
        
9.permission:android.permission.READ_SMS 
        permission:android.permission.RECEIVE_WAP_PUSH 
        permission:android.permission.RECEIVE_MMS 
        permission:android.permission.RECEIVE_SMS 
        permission:android.permission.SEND_SMS 
        permission:android.permission.READ_CELL_BROADCASTS
~~~~
 