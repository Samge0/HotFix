package com.example.samge.hotfix.application;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Created by samge on 17-7-13.
 */

public class BaseTinkerApplication extends TinkerApplication {
    public BaseTinkerApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.example.samge.hotfix.application.ApplicationLike");
    }
}