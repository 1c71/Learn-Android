package com.example.agoodob.smsfirewall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 2016-2-27
 * 短信防火墙 - 通过广播，拦截指定号码的短信
 *
 * 视频里好像用的 Android 2.3 测试成功
 * 在 Emulator 里, Nexus 5, API23, Android 6.0 上测试失败, 无法拦截
 * 可能是安全性改进了?
 *
 * 查了下看到有博客写:
    http://blog.csdn.net/crazy__chen/article/details/49474381
    下面我再来说明一下google对短信机制的修改，首先一个原则是, 4.4及其以后系统，
    只能设置一个默认的SMS短信app，但短信到达，首先会通知这个app，
    并且只有这个app有对短信数据库的修改权限和短信的发送权限
    并且短信广播，不再是有序广播，也就是App没有办法拦截这个广播，所有app都快接收到短信到达的广播通知，
    但是只有默认SMS短信app可以修改短信记录
    但是！不排除有些操作系统，例如小米会修改这个机制！

 所以解决办法是吧你的 APP 选择成默认的短信处理 APP
 短信处理 APP 这个你又得稍微多写点东西


 http://blog.csdn.net/l173864930/article/details/17019615

 总结：安全类应用要在4.4上实现短信拦截，需要引导用户把自身选择为default sms。
 但需要注意，一旦用户选择了自身作为default sms，那系统的“短信”应用则完全不工作，
 这意味着安全类应用需要承担起真正的短信解析、发送、呈现等等功能以及单卡双卡等适配工作。
 对于同步类应用，一个建议就是在短信恢复前，先引导用户把自身设置成default sms，恢复成功后再还原（4.4有提供相关的API）。

 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
