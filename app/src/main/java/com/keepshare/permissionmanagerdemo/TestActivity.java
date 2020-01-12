package com.keepshare.permissionmanagerdemo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.keepshare.permission.PermissionManager;
import com.keepshare.permission.Result;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/**
 * @Author: Johnny
 * @Email keepshare@163.com
 * @CreateDate: 2020-01-12
 * @Copyright: keepshare, All rights reserved
 * @Description:
 */
public class TestActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PermissionManager.init(this)
                .request()
                .requestCallback(new Function1<Result, Unit>() {
                    @Override
                    public Unit invoke(Result result) {
                        return null;
                    }
                });

    }
}
