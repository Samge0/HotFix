使用Bugly接入Tinker热更新的再次封装，方便开发者给应用快速加入热修复功能。

步骤：
1.注册腾讯Bugly，并申请产品，得到属于自己的buglyId

2.导入tinkerlib到目标项目并添加依赖

3.在根目录gradle.properties文件下增加：

    #适配7.0访问文件，每个APP的这个需要是唯一值，不然会安装失败。可以随意填写，建议使用报名
    FILEPROVIDER_AUTHORITIES=com.example.samge.hotfix

    #bugly版本配置+tinker版本配置
    BUGLY_CRASHREPORT_UPGRADE=1.3.5
    BUGLY_NATIVECRASHREPORT=3.3.1
    BUGLY_TINKER_SUPPORT=1.1.2
    TINKER_VERSION=1.9.6
    BUGLY_ID=这里填写你的buglyId

4.仿造Demo根部的build.gradle添加依赖

5.在主项目的build.gradle添加 api project(':tinkerlib') 依赖

6.改造主项目的application，可以模仿Demo的application相关配置

7.完成热修复能力的接入，测试并验证

（PS：如遇到问题，多数跟gradle版本不一致等导致，具体参考Demo，根据你自己的实际应用进行相应调整即可）