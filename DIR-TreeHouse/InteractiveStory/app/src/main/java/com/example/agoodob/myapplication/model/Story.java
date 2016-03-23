package com.example.agoodob.myapplication.model;

import com.example.agoodob.myapplication.R;

/**
 * Created by agoodob on 2016/1/15.
 */
public class Story {
    private Page[] mPages;

    // 构造函数
    public Story() {
        mPages = new Page[7];

        mPages[0] = new Page(
                R.drawable.page0,
                //"On your return trip from studying Saturn's rings, you hear a distress signal that seems to be coming from the surface of Mars. It's strange because there hasn't been a colony there in years. Even stranger, it's calling you by name: \"Help me, %1$s, you're my only hope.\"",
                "在你研究完土星环之后, 你在回地球的路上收到呼救信号, 似乎信号来自于火星表面. 但奇怪的是没人住在火星. 更奇怪的是他还知道你的名字: \\\"救救我, %1$s, 你是我唯一的希望了.\\\"",
//                new Choice("Stop and investigate", 1),
                new Choice("停下并调查一下", 1),
                new Choice("继续回地球", 2));

        mPages[1] = new Page(
                R.drawable.page1,
                // "You deftly land your ship near where the distress signal originated. You didn't notice anything strange on your fly-by, but there is a cave in front of you. Behind you is an abandoned rover from the early 21st century.",
                "你把船停到了呼救信号的附近. 你没在降落的途中看到什么怪事, 但你看到你面前有一个洞穴, 而你背后是一辆21世纪时生产的车.",
                //new Choice("Explore the cave", 3),
                new Choice("探索洞穴", 3),
               //new Choice("Explore the rover", 4));
                new Choice("探索汽车", 4));

        mPages[2] = new Page(
                R.drawable.page2,
                //"You continue your course to Earth. Two days later, you receive a transmission from HQ saying that they have detected some sort of anomaly on the surface of Mars near an abandoned rover. They ask you to investigate, but ultimately the decision is yours because your mission has already run much longer than planned and supplies are low.",
                "你继续飞回地球.两天之后, 你收到了来自总部的信号说他们在火星表面探测到一部车, 并且在车的附近探测到不寻常的东西. 他们希望你能去调查一下, 不过最终决定权还是在你, 因为你的上一个任务的执行时间远远超出了计划, 而且你的补给也不多了.",
//                new Choice("Head back to Mars to investigate", 4),
                new Choice("去火星调查一下", 4),
                //new Choice("Continue home to Earth", 6));
                new Choice("继续回地球", 6));

        mPages[3] = new Page(
                R.drawable.page3,
                // "Your EVA suit is equipped with a headlamp, which you use to navigate the cave. After searching for a while your oxygen levels are starting to get pretty low. You know you should go refill your tank, but there's a very faint light up ahead.",
                "你的太空服头部有个灯, 你用来在洞穴里照明. 在洞穴里探索了一会儿之后, 你的氧气没剩多少了, 你知道你应该回去补充氧气, 但前面有一阵弱弱的灯光.",
                //new Choice("Refill at ship and explore the rover", 4),
                new Choice("回去补给氧气", 4),
                //new Choice("Continue towards the faint light", 5));
                new Choice("走向那一束淡淡的光", 5));

        mPages[4] = new Page(
                R.drawable.page4,
                //"The rover is covered in dust and most of the solar panels are broken. But you are quite surprised to see the on-board system booted up and running. In fact, there is a message on the screen: \"%1$s, come to 28.543436, -81.369031.\" Those coordinates aren't far, but you don't know if your oxygen will last there and back.",
                "这辆漫游者到处都是灰尘, 而且大部分太阳能板都坏了, 但你惊喜的看到车载系统居然还在运行. 而且屏幕上显示着: \"%1$s, 请来坐标 28.543436, -81.369031.\" 坐标所指的位置离你不远, 但你不知道你的氧气是否足够你走过去再回来.",
                //new Choice("Explore the coordinates", 5),
                new Choice("去那个坐标看看", 5),
                //new Choice("Return to Earth", 6));
                new Choice("返回地球", 6));

        mPages[5] = new Page(
                R.drawable.page5,
                //"After a long walk slightly uphill, you end up at the top of a small crater. You look around, and are overjoyed to see your favorite android, %1$s-S1124. It had been lost on a previous mission to Mars! You take it back to your ship and fly back to Earth.");
                "在走上一个山坡之后, 你在火山口的顶端四处张望, 惊喜的看到你的最喜欢的机器人, %1$s-S1124. 上次火星任务时丢失的. 你把它带回飞船然后飞回了地球.");
        mPages[6] = new Page(
                R.drawable.page6,
                // "You arrive home on Earth. While your mission was a success, you forever wonder what was sending that signal. Perhaps a future mission will be able to investigate...");
                "你回到了地球的家中, 虽然你的任务很成功, 但你还是好奇是谁发的求救信号. 也许下次出任务有机会去调查下...");
    }

    public Page getPage(int pageNumber){
        return mPages[pageNumber];
    }
}
