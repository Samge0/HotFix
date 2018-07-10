package com.samge.tools.tinkercomponent.base;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDex;

import com.samge.tools.tinkercomponent.BuildConfig;
import com.samge.tools.tinkercomponent.util.TinkerUtil;
import com.tencent.bugly.beta.Beta;
import com.tencent.tinker.loader.app.DefaultApplicationLike;


/**
 * Created by samge on 17-7-13.
 */

public class BaseTinkerApplicationLike extends DefaultApplicationLike {
    private static Application mContext;
    public BaseTinkerApplicationLike(Application application, int tinkerFlags,
                                     boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime,
                                     long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplication();

        TinkerUtil.initBugly(getApplication(), BuildConfig.BUGLYID, BuildConfig.DEBUG);
        onTinkerApplicationCreate();
    }

    /**
     * 子类使用此方法进行其他初始化
     */
    protected void onTinkerApplicationCreate() {

    }

    /**
     * 获取上下文
     * @return
     */
    public static Application getContext(){
        return mContext;
    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        MultiDex.install(base);
        Beta.installTinker(this);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
        getApplication().registerActivityLifecycleCallbacks(callbacks);
    }
}

