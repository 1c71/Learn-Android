package com.example.agoodob.mobilesafe1c7;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 2016/2/25 第一次创建
 * 2016/2/26 更新
 */
public class SettingsItemView extends RelativeLayout {

    //private static final String NAMESPACE = "http://schemas.android.com/apk/res/com.example.agoodob.mobilesafe1c7";
    private static final String NAMESPACE = "http://schemas.android.com/apk/res-auto";
    private String mDescOn;
    private String mDescOff;
    private String mTitle;
    private TextView tvTitle;
    private CheckBox cbStatus;
    private TextView tvDesc;

    public SettingsItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 处理自定义属性
//        int attrCount = attrs.getAttributeCount();
//        for (int i =0; i<attrCount; i++){
//            String name = attrs.getAttributeName(i);
//            String value = attrs.getAttributeValue(i);
//        }
        // 获取自定义属性的值
       // attrs.getAttributeValue()
        mTitle = attrs.getAttributeValue(NAMESPACE, "mTitle");
        mDescOff = attrs.getAttributeValue(NAMESPACE, "desc_off");
        mDescOn = attrs.getAttributeValue(NAMESPACE, "desc_on");
        System.out.println(mTitle + " " + mDescOff + " " + mDescOn);
        initView();
    }

    public SettingsItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public SettingsItemView(Context context) {
        super(context);
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView(){
        // 讲自定义好的布局文件设置给当前的 SettingsItemView
        View.inflate(getContext(), R.layout.view_setting_item, this); // ---------------------
        tvTitle = (TextView) findViewById(R.id.tv_title);
        cbStatus = (CheckBox) findViewById(R.id.checkBox);
        tvDesc = (TextView) findViewById(R.id.tv_desc);

        setTitle(mTitle);
    }

    public void setTitle(String title){
        tvTitle.setText(title);
    }

    public void setDesc(String desc){
        tvDesc.setText(desc);
    }

    public boolean isChecked(){
       return  cbStatus.isChecked();
    }

    public void setChecked(boolean check){
        cbStatus.setChecked(check);
        // 根据勾选状态更新文本
        if (check){
            setDesc(mDescOn);
        } else {
            setDesc(mDescOff);
        }
    }
}
