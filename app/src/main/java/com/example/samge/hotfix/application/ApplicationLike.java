package com.example.samge.hotfix.application;

import android.app.Application;
import android.content.Intent;

import com.samge.tools.tinkercomponent.base.BaseTinkerApplicationLike;

/**
 * Created by mrzhang on 2017/6/15.
 * <p>
 * edit by samge on 2018/07/06
 */

public class ApplicationLike extends BaseTinkerApplicationLike {
    public ApplicationLike(Application application, int tinkerFlags,
                                     boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime,
                                     long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }
}