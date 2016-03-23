#### 这些内容是原先放在 README.MD 里的
但是后面发现有些不那么重要的内容拉长了整个 README.MD<br/>
所以就放到这里


<br/>
### 5. Android Activity Lifecycle
首先学了横竖屏切换 Activity 会销毁重建, 然后基于 Fun Fact 改进了切换到横屏的时候保存当前的冷知识, 不会回复到第一个.
第二个项目是高尔夫计分板.

讲了:

    1. 当竖屏切换到横屏的时候, Activity 会重新创建, 所以必须保存状态, 然后恢复
    http://developer.android.com/training/basics/activity-lifecycle/recreating.html

    用 onSaveInstanceState 保存状态
        outState.putString(KEY, VALUE);
        outState.putInt(KEY, VALUE);

    用 onRestoreInstanceState 恢复状态
        savedInstanceState.getString(KEY,VALUE);
        savedInstanceState.getInt(KEY,VALUE);

    2. Activity 有不同的状态
    http://stackoverflow.com/questions/8515936/android-activity-life-cycle-what-are-all-these-methods-for
    https://developer.xamarin.com/guides/android/application_fundamentals/activity_lifecycle/

        onCreate()
        onStart()
        onRestart()
        onResume()
        onPause()
        onStop()
        onDestory()

    3. 用 SharedPerferences 保存数据, 这样下次关掉 APP 再打开也能拿到数据
        1. xShare = getSharedPreferences(aa, Context.MODE_PRIVATE);
        2. xEdit = xShare.edit();

        在 onPause() 里
        mEdit.putString(bb, value) 或者 putBoolean, putInt..
        mEdit.apply(); //保存修改

        如果要清掉所有数据就
        mEdit.clear();
        mEdit.apply();

<br/>
### 4. 改进 Stormy  - 用列表显示天气数据
课程名: Android Lists and Adapters

<img src="README/41.jpg" width="218">
<img src="README/42.jpg" width="218">
<img src="README/43.jpg" width="218">
<img src="README/44.jpg" width="218">


讲了:

    1. Adapter 的简单应用和自定义 Adapter
        不过我没完全理解

    2. implements Parcelable
        数据打包什么的, 我在这里也没理解

    3. RecyclerView
        要去 build.gradle 的 dependencies 里加一行    compile 'com.android.support:recyclerview-v7:23.1.1'

        <android.support.v7.widget.RecyclerView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/reyclerView"/>


<br/>
### 3. Stormy (天气信息)
课程名: Build a Weather App

<img src="README/31.jpg" width="293">
<img src="README/32.jpg" width="293">

讲了:

    1. 什么是 API

    2. 联网的权限 和检查网络情况的权限, 要在 AndroidMainfest.xml 里写申请
        <uses-permission android:name="android.permission.INTERNET" />
        <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>

    2. UI 线程, 后台线程. 耗时的, 和界面无关的任务都放到后台线程里去做, 不然界面就卡住不动了.

    3. 第三方库怎么用 (去 build.gradle 里的 dependenices 里添加一行就好)

        dependencies {
            compile fileTree(dir: 'libs', include: ['*.jar'])
            testCompile 'junit:junit:4.12'
            compile 'com.android.support:appcompat-v7:23.1.1'
            compile 'com.squareup.okhttp3:okhttp:3.0.1'
            compile 'com.jakewharton:butterknife:7.0.1'
        }

    4. 第三方库 OkHttp (网络请求库)

            String forecastUrl = "http://www.something.com";
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(forecastUrl)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback(){
                ... 里面太长就不写全了..
                })


    5. 第三方库 ButterKnife
        @Bind(R.id.xxx) (TextView) mTimeLabel;
        Butterknife.bind(this);

    6. 系统弹窗怎么做  AlertDialogFragment extends DialogFragment

    7. JSON 解析 ( JSONObject )

    8. SimpleDataFormat 处理时间戳.

        ```
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE");
        formatter.setTimeZone(TimeZone.getTimeZone(mTimezone));
        Date dateTime = new Date(mTime * 1000);
        return formatter.format(dateTime);
        ```

    9. layout:weight  在上级元素中占据的宽度比例

    10. gravity  对齐

    11. runOnUiThread(new Runnable() { .... })


<br/>
### 2. Interactive Story (飞船故事)
课程名:《Build an Interactive Story App》  <br/>

官方设计稿: <br/>
<img src="README/interactive_story_mockups.png">

玩法是一开始让你输入一个名字, 然后进入一个故事, 每个故事有2个按钮, 然后进到不同的故事 <br/>
真机测试的截图(我汉化了下)<br/>
<img src="README/21.jpg" width="174">
<img src="README/22.jpg" width="174">
<img src="README/23.jpg" width="174">
<img src="README/24.jpg" width="174">
<img src="README/25.jpg" width="174">
<img src="README/26.jpg" width="174">
<img src="README/27.jpg" width="174">
<img src="README/28.jpg" width="174">
<img src="README/29.jpg" width="174">


这个课是第 2 篇, 用了 2 个 Activity  <br/>
讲了:

    1. @Override 不是必写的, 但是写了更好, 表明了你要覆盖掉父类的一个方法,
    如果你不小心写错大小写, 导致和父类想覆盖的那个方法名字不一样, 比如 i 和 l, 肉眼难辨认, 可能就会导致问题

    2. Activity 之间怎么切换 (Intent)

    3. 切换 Activity 时怎么传递数据 ( Intent.putExtra() )

    4. MVC 的概念: 用了游戏主机的手柄比喻 Controller, 游戏主机比作 Model, 显示器比作 View

    5. 在 APP 中实际用 MVC 来让代码更好理解和维护
        建 package, 名字叫 model, ui, 等等


<br/>
### 1. Fun Facts (有趣冷知识)
课程名: 《Build a Simple Android App》<br/>
FunFacts/ 是 TreeHouse Android 系列教程的第 1 篇<br/>
点击按钮，切换不同的有趣的冷知识.<br/>
<img src="README/1.jpg" width="174">
<img src="README/2.jpg" width="174">
<img src="README/3.jpg" width="174">
<img src="README/4.jpg" width="174">
<img src="README/5.jpg" width="174">

讲了:

    1. Android Studio 怎么用
        (在 Project 视图和 Android 视图间切换)

    2. 模拟器在哪里开

    3. 怎么去掉顶部的 Action Bar
        (res/values/styles.xml) parent="Theme.AppCompat.Light.NoActionBar"

    4. strings.xml 是什么
        (1.便于多语言 2.避免重复在代码里写死文字, 同样的文字跨越数个文件的不同地方, 很难改, 容易改漏)

    5. findViewById 通过 ID 找 View.

    6. OnClickListener 监听点击事件

    7. Random 生成随机数.
        Random random = new Random();
        int x = random.nextInt(10);

    8. Toast 就是屏幕下方出现的黑色背景小提示框
        Toast.makeText(this, 文字在此, Toast.LENGTH_LONG).show();
        第一个参数是上下文, 第二个是字符串, 第三个只能选 LENGTH_LONG 或者 LENGTH_SHORT，不能自定义显示时长

    9. 怎么改 APP ICON
        res/mipmap/ic_launcher.png



<br/>

---

<br/>


#### 环境

    Windows 10 x64
    Android Studio 1.5.1
    Sumsung Note 5 真机调试 (Android 5.1)



<br/>
#### 9. 零碎
Ctrl+Q 看函数文档很重要

#### 推荐资料

    什么是 Bundle?
    http://stackoverflow.com/questions/4999991/what-is-a-bundle-in-an-android-application
        Bundle 用于不同 Activity 之间传递数据


#### 文件夹

    FunFacts/             有趣冷知识 APP

    InteractiveStory/     太空探险故事 APP

    GolfCard/             高尔夫18洞计分 APP

    SelfDestructMessage/  Snapchat 模仿 APP

    SharePerfences/       练习 SharePerferences 的 APP

    Stormy/               今日天气 APP

    StormyList/           今日天气 + 今天的每小时天气(列表) + 本周每天的天气(列表)

    README/               跟安卓无关, 只是 README 里需要用到的图片

    太多懒得写了




#### 学习资料来源

1 Udacity  - 看了几个视频就跟不下去了
2 Treehouse - 看了两个新手入门系列
3 老罗 - 看了部分
4 Mars - 看了部分
5 黑马52期 - 有很多没看完
6 Android 编程权威指南 - GeoQuiz, CrimeIntent, HelloMoon
7 Udacity NanoDegree


