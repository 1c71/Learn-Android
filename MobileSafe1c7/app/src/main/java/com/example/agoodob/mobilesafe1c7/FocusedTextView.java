package com.example.agoodob.mobilesafe1c7;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 获取焦点的 TextView
 * 2016/2/25.
 */
public class FocusedTextView extends TextView{

    // 用代码 new 的时候走此方法
    public FocusedTextView(Context context) {
        super(context);
    }

    // 有属性时走此方法
    public FocusedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // 有 style 样式的话会走此方法
    public FocusedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 表示有无获取焦点
     * 跑马灯要运行首先要用此函数判断是否有焦点
     * 所以我们不管实际上有没有焦点，都强制返回true让跑马灯认为有焦点
     * @return
     */
    public boolean isFocused(){
        return true;
    }

}
