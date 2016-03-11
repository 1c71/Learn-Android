#### 安卓学习代码库
    我学安卓过程中的每个 project 都存在这里了.  <br/>
    这里每个文件夹都是一个 project, 就是备个份  <br/>


#### 开发环境

    Windows 10 x64
    Android Studio 1.5.1
    Sumsung Galaxy Note 5 真机调试 (Android 5.1.1)



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
#### 10. 震动
```
        Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        v.vibrate(2000);
        // 震动2秒

        v.vibrate(new long[]{1000, 2000, 1000, 3000}, -1);
        // 前面的 long 数组代表震动的模式,  -1 表示只执行一次
```

权限:
    <uses-permission android:name="android.permission.VIBRATE"/>



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

Udacity: Weather
Treehouse: Android Track
黑马
老罗
Mars


学完之后整理下 Evernote 再写这里

<br/>
#### 下拉刷新的做法

前提知识:
ListView 有一个方法叫做 addHeaderView, 就是在列表的头部加一个视图.
你可以自己写个布局文件然后 addHeaderView, 这个布局就到了列表头部了.


下拉刷新做法:

    1) 用 ListView 的 addHeaderView 在列表头部加个视图

    2) 给 headerView 设置 负的 paddingTop. 这样 headerView 就躲到 ListView 后面去了
    headerView.setPadding(0, -headerViewHeight, 0, 0);

    3) 监听 onTouchEvent, 在这里面写:
        1- 当手指摸到屏幕时保存 y 坐标
        (在 MotionEvent.ACTION_DOWN 里写 )

        2- 当手指移动的时候获得 y 坐标的改变值, 比如这人是不是按着屏幕向下拉了20个像素,
        根据这个拉动距离改变 -paddingTop 的值, 从而将之前隐藏的视图显示出来.
        (在 MotionEvent.ACTION_MOVE 里面写)

        3- 当手指抬起屏幕之后, 根据拉动的距离, 判断是不是大于下拉刷新那块区域的高度.
        以此判断是收回去, 无需刷新. 或者是需要刷新.
        (在 MotionEvent.ACTION_UP 里面写)


    [注意点]
    可以做几个状态常量，来表示不同状态
    (在 MotionEvent.ACTION_MOVE 里面根据拖动距离去修改状态)
    松手时在 MotionEvent.ACTION_UP 根据状态做出响应
    例子:

    private final int PULL_REFRESH = 0; // 下拉刷新的状态
    private final int RELEASH_REFRESH = 1; // 松开状态
    private final int REFRESHING = 2;  // 刷新中





<br/>
#### 侧滑菜单的做法

    1) 弄个 class extends ViewGroup

    2) 弄个 view (比如layout/slide_menu.xml)
        里面用 ScrollView
没写完


<br/>
#### APP 载入页面怎么做? (比如微信的地球和人暗色图片)
这种叫 Splash 页面,
做法是用一个 Activity 做, 定时显示数秒, 然后跳到另一个 Activity

<br/>
#### 引导页面怎么做? (出现于第一次打开某APP, 然后可以左右滑动, 有的有些动画效果)

1) ViewPager 可以做到左右滑动的效果

2) 至于判断是否第一次打开, 就用 SharePreference

3) 有时候你会看到圆点在页面下方，那个用 RelativeLayout 包一个 LinearLayout
Linearlayout 里面放3个小灰点
于Linearlayout 同级的地方放一个 view 标签做小红点

监听 viewpager 的滑动事件，算出百分比



<br/>
#### 关于模拟器
Genymotion 这个安卓模拟器有免费版的，仅供个人使用，开发的可以下载下来用


<br/>
#### 前端和安卓的异同点


android 空间的 visibility
gone
invisibilty
















第三方库安装方法(Android Studio):

     方法1  build gradle 里去黏贴一行 complie xxxx，作者肯定会提供这个，你肯定不用写，复制粘贴就好
     方法2 下载压缩包, 解压之后文件夹里会有个 .jar  扔到 app/libs 目录里即可, 记得右键  Add as Libary(在右键菜单下方那一块) 不然不行

