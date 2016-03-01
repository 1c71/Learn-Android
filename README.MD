### 安卓重要概念极速入门
(未完, 很乱还没整理)
适合阅读人群: 任何对移动开发有一定兴趣的人
配图吗?

这里只列出最重要的概念, 具体的细节不描述, 不然就成了教程了, 那就得写很长了. <br/>
看完这些概念之后你也大概了解了是怎么回事, 可以自己去看教程深入. [教程推荐在此]()<br/>


0 安卓开发用的语言是 Java, 所以如果你没有任何编程基础的话, 那就必须先学 Java
1 现在的开发工具有 Eclipse + ADT 插件 以及 Android Studio 两种
建议直接用 Android Studio, Google 亲儿子


2 网上各种教程很多

打开 Android Studio


2 目录概述
res/
layout/
values/

生命周期的概念 onStart,


AndroidManifest.xml 是负责这个 APP 的各种信息。
Service 写里面，广播的注册 <receiver></receiver>  也写在里面，权限的申请也写在里面。<receiver></receiver>
最常用的权限是网络权限, 写法是: ....

2 一个 Activity 就是一个页面

3 横屏竖屏切换的时候会杀死 Activity 然后再开

3 事件监听..

3 页面之间的跳转用 Intent
比如你可以新建一个 SecondActivity, 然后在 MainA

4 Intent 不只是跳转页面
Intent 也可以用来发广播

广播 Broadcast
用途:


服务 Service


存储

发短信

第三方库的安装
jar 和 compile

第三方库的使用

目前很多人用的第三方库

教程推荐
    先说下我都看了些啥
    然后最后说我的推荐



<br>
#### 10.






<br>
#### 9. 安卓的存储
1 存手机本身的存储里面, 这个叫 Internal Storege
2 External Storage, 其实就是 SD 卡
3 网络存储，其实就是发请求到服务器自己存起来
4 SharePerference - 如果你有少量的 key value 数据要存就用这个, 官方文档说的
5 SQLite 数据库


<br/>
#### 8. 打电话

打电话要权限
AndroidManifest.xml
<uses-permission android:name="android.permission.CALL_PHONE"/>

```java
Intent i = new Intent();
i.setAction(Intent.ACTION_CALL);
i.setData(Uri.parse("tel:" + number));
startActivity(i);
```
number 就是电话号码

<br/>
#### 7. 发短信
先在布局文件里自己拉两个 EditText 然后:

```java
// 拿到手机号
EditText et_phone = (EditText) findViewById(R.id.sendTextNumber);
String phone = et_phone.getText().toString();

// 拿到短信内容
EditText et_content = (EditText) findViewById(R.id.sendTextContent);
String content = et_content.getText().toString();

SmsManager sm = SmsManager.getDefault();
sm.sendTextMessage(phone, null, content, null, null);
// 第二个参数是 SMSC 短信中心，不用管它，不设置才对
// 第四个参数是 pendingIntent
// 第五个参数是 deliveryIntent
```

<br/>
#### 6. 上线 Google Play
国内用不了不过可以了解下

    [钱]:    要给 Google 25 美金.
    [审核]:    不像 Apple 需要审核, Google Play 可以直接上线.
    [如果是 Google Play 付费APP]:    要申请个 Wallet Account
    好像和苹果一样都是收掉 30% 的收入?


<br/>
#### 教程推荐
首先建议去知乎, 简书上搜索 "安卓", "安卓教程" 之类的关键词


<br/>
#### 为什么会有这篇东西

[配图版点这里]()

Udacity Weather
Treehouse Android
黑马, 老罗, Mars


学完之后整理下 Evernote 再写这里




<br/>
#### 前端和安卓的异同点
