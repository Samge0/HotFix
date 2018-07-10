package com.samge.tools.tinkercomponent.util;

import android.content.Context;
import android.util.Log;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;
import com.tencent.tinker.lib.util.TinkerLog;

/**
 * Created by samge on 18-5-9.
 * describe ：
 */

public class TinkerUtil {

    /**
     * 检查补丁及用bugly检查应用更新-放在最前的地方，防止其他意外
     */
    public static void checkTinkerPatch() {
        Beta.checkUpgrade(false, false);//
    }

    /**
     * 初始化腾讯bugly
     */
    public static void initBugly(Context context,String buglyId,boolean isDebug) {
        if(!isDebug) {
            TinkerLog.setTinkerLogImp(null);
        }
        //设置升级样式
        TinkerUtil.checkTinkerPatch();
        Beta.autoCheckUpgrade = true;//自动检查更新开关
        //Beta.canShowUpgradeActs.add(MainActivity.class);//添加可显示弹窗的Activity
        Beta.autoDownloadOnWifi = true;//设置Wifi下自动下载
        Beta.canShowApkInfo = false;//设置是否显示弹窗中的apk信息
        Beta.enableNotification = false;//设置是否在通知栏显示下载进度
        //设置补丁包监听
        Beta.betaPatchListener = new BetaPatchListener() {
            @Override
            public void onPatchReceived(String patchFile) {
                Log.i("tinker","补丁下载地址" + patchFile);
            }
            @Override
            public void onDownloadReceived(long savedLength, long totalLength) {
            }
            @Override
            public void onDownloadSuccess(String msg) {
                Log.i("tinker","补丁下载成功");
            }
            @Override
            public void onDownloadFailure(String msg) {
                Log.i("tinker","补丁下载失败");
            }
            @Override
            public void onApplySuccess(String msg) {
                Log.i("tinker","补丁应用成功");
            }
            @Override
            public void onApplyFailure(String msg) {
                Log.i("tinker","补丁应用失败");
            }
            @Override
            public void onPatchRollback() {
                Log.i("tinker","补丁回滚");
            }
        };
        Bugly.init(context, buglyId, isDebug);
    }

    /**
     * 设置热更新的测试设备
     * @param contxt
     */
    /*public static void setBuglyDebugDevice(Context contxt) {
        String imei=SystemUtils.getIMEI(contxt);
        if ("860286032384590".equals(imei) || "860286032384594".equals(imei)
                || "865774020485326".equals(imei) || "865774020485327".equals(imei)
                || "866032026315123".equals(imei) || "868029027630169".equals(imei)) {
            LogUtils.i("此为测试设备：IMEI==" + imei);
            Bugly.setIsDevelopmentDevice(UIUtils.getContext(), true);//标记测试设备
        }
    }*/
}
