package com.example.agoodob.news1c7.base;

import com.example.agoodob.news1c7.domain.NewsData.NewsTabData;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * 页签详情页
 * 
 * @author Kevin
 * 
 */
public class TabDetailPager extends BaseMenuDetailPager {

	NewsTabData mTabData;
	private TextView tvText;

	public TabDetailPager(Activity activity, NewsTabData newsTabData) {
		super(activity);
		mTabData = newsTabData;
	}

	@Override
	public View initViews() {
		tvText = new TextView(mActivity);
		tvText.setText("页签详情页");
		tvText.setTextColor(Color.RED);
		tvText.setTextSize(25);
		tvText.setGravity(Gravity.CENTER);
		return tvText;
	}

	@Override
	public void initData() {
		tvText.setText(mTabData.title);
	}

}
