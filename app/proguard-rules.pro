# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# banner 的混淆代码

-keep class com.youth.banner.** {
    *;
 }

-keepattributes Signature
-ignorewarnings
-keep class javax.ws.rs.** { *; }
-keep class com.alibaba.fastjson.** { *; }

-dontwarn com.alibaba.fastjson.**
-keep class sun.misc.Unsafe { *; }
-dontwarn sun.misc.**
-keep class com.taobao.** {*;}
-keep class com.alibaba.** {*;}
-keep class com.alipay.** {*;}
-dontwarn com.taobao.**
-dontwarn com.alibaba.**
-dontwarn com.alipay.**
-keep class com.ut.** {*;}
-dontwarn com.ut.**
-keep class com.ta.** {*;}
-dontwarn com.ta.**
-keep class org.json.** {*;}
-keep class com.ali.auth.**  {*;}
-dontwarn com.ali.auth.**
-keep class com.taobao.securityjni.** {*;}
-keep class com.taobao.wireless.security.** {*;}
-keep class com.taobao.dp.**{*;}
-keep class com.alibaba.wireless.security.**{*;}
-keep interface mtopsdk.mtop.global.init.IMtopInitTask {*;}
-keep class * implements mtopsdk.mtop.global.init.IMtopInitTask {*;}

    -keepattributes Signature
     -keep class sun.misc.Unsafe { *; }
     -keep class com.taobao.** {*;}
     -keep class com.alibaba.** {*;}
     -keep class com.alipay.** {*;}
     -dontwarn com.taobao.**
     -dontwarn com.alibaba.**
     -dontwarn com.alipay.**
     -keep class com.ut.** {*;}
     -dontwarn com.ut.**
     -keep class com.ta.** {*;}
     -dontwarn com.ta.**
     -keep class org.json.** {*;}
     -keep class com.ali.auth.**  {*;}

     -keep class com.tencent.mm.opensdk.** {
         *;
     }

     -keep class com.tencent.wxop.** {
         *;
     }

     -keep class com.tencent.mm.sdk.** {
         *;
     }

     # 微信支付 -dontwarn com.tencent.mm.**
     -dontwarn com.tencent.wxop.stat.**
     -keep class com.tencent.wxop.stat.**{*;}


     ## keep class 包名.* 保持包名下的所有类不被混淆 -dontwarn com.tencent.mm.**
     -keep class com.tencent.mm.**{*;}
     -dontwarn com.sina.**
     -keep class com.sina.**{*;}

     -keepattributes *Annotation*
     -keepclassmembers class * {
         @org.greenrobot.eventbus.Subscribe <methods>;
     }
     -keep enum org.greenrobot.eventbus.ThreadMode { *; }

     # Only required if you use AsyncExecutor
     -keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
         <init>(java.lang.Throwable);
     }
-keep class android.content.**{*;}
-keepclassmembers class * extends android.app.Activity {public void *(android.view.View);}
-keepclassmembers enum * {public static **[] values();public static ** valueOf(java.lang.String);}
-keep class com.baidu.mobads.*.** { *; }

-dontwarn android.support.v4.**
-keep class android.support.**{*;}
-keep interface android.support.v4.app.**{*;}
# 保留继承的
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment
-keep public class * extends android.support.v7.**
-keep public class * extends android.support.annotation.**

#不进行混淆保持原样
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
# 保留R下面的资源
-keep class **.R$* {*;}
# 保留在Activity中的方法参数是view的方法，
# 这样以来我们在layout中写的onClick就不会被影响
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}

#腾讯bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
-keep class android.support.**{*;}
# tinker混淆规则
-dontwarn com.tencent.tinker.**
-keep class com.tencent.tinker.** { *; }

# 保留枚举类不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
# 保留我们自定义控件（继承自View）不被混淆
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
# 保留Parcelable序列化类不被混淆
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# 保留Serializable序列化的类不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
# 对于带有回调函数的onXXEvent、**On*Listener的，不能被混淆
-keepclassmembers class * {
    void *(**On*Event);
    void *(**On*Listener);
}
 #webView处理，项目中没有使用到webView忽略即可
 -keepclassmembers class fqcn.of.javascript.interface.for.webview {
     public *;
 }
 -keepclassmembers class * extends android.webkit.webViewClient {
     public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
     public boolean *(android.webkit.WebView, java.lang.String);
 }
 -keepclassmembers class * extends android.webkit.webViewClient {
     public void *(android.webkit.webView, jav.lang.String);
 }

# Retrofit
-dontwarn retrofit2.** -keep class retrofit2.** { *; }
-keepattributes Signature -keepattributes Exceptions # okhttp
-dontwarn okio.** # RxJava RxAndroid
-dontwarn sun.misc.** -keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
   rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
  rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

#穿山甲广告
-keep class com.bytedance.sdk.openadsdk.** { *; }
-keep public interface com.bytedance.sdk.openadsdk.downloadnew.** {*;}



##########################客服系统start##########################
-keep class com.kf5.sdk.im.entity.**{*;}
-keep class com.kf5.sdk.helpcenter.entity.**{*;}
-keep class com.kf5.sdk.system.entity.**{*;}
-keep class com.kf5.sdk.ticket.entity.**{*;}
-keep class com.kf5.sdk.im.expression.**{*;}
#glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
 **[] $VALUES;
 public *;
}
#gson
-keep class com.google.gson.** {*;}
-keep class com.google.**{*;}
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
##########################客服系统end##########################

# Gson
# 自定义数据模型的bean目录
-keep class com.wuxiantao.wxt.bean.**{*;}
-keep class com.wuxiantao.wxt.adapter.bean.**{*;}



#是否混淆第三方jar
-dontskipnonpubliclibraryclasses

# 代码混淆压缩比，在0~7之间，默认为5，一般不做修改
-optimizationpasses 5

# 这句话能够使我们的项目混淆后产生映射文件
# 包含有类名->混淆后类名的映射关系
-verbose

# 忽略警告，避免打包时某些警告出现
-ignorewarnings

 #指定不去忽略非公共库的类成员
-dontskipnonpubliclibraryclassmembers

# 保留Annotation不混淆
-keepattributes *Annotation*,InnerClasses

# 避免混淆泛型
-keepattributes Signature

# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable

# 指定混淆是采用的算法，后面的参数是一个过滤器
# 这个过滤器是谷歌推荐的算法，一般不做更改
-optimizations !code/simplification/cast,!field/*,!class/merging/*


#友盟混淆
-keep class com.umeng.** {*;}
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

