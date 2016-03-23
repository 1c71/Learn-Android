package com.example.agoodob.pull2fresh;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  下拉刷新的ListView
 */
public class RefreshListView extends ListView implements AbsListView.OnScrollListener {

    private View headerView;
    private View footerView;
    private int headerHeight;
    private int footerHeight;

    private int downY; // 按下时的 Y 坐标
    private final int PULL_REFRESH = 0; // 下拉刷新的状态
    private final int RELEASH_REFRESH = 1; // 松开状态
    private final int REFRESHING = 2;  // 刷新中
    private int currentState = PULL_REFRESH;

    private ImageView iv_arrow;
    private ProgressBar pb_rotate;
    private TextView tv_state, tv_time;
    private RotateAnimation upAnim, downAnim; // 翻转箭头的动画
    private boolean isLoadingMore = false;

    // 接口和回调
    private OnRefreshListener listener = null;
    public void setOnRefreshListener(OnRefreshListener listener){
        this.listener = listener;
    }

    public interface OnRefreshListener{
        void onPullRefresh();
        void onLoadingMore();
    }

    // 构造方法
    public RefreshListView(Context context) {
        super(context);
        init();
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init(){
        setOnScrollListener(this);
        initHeader();
        initFooter();
        initRotateAnimation();
    }

    /**
     * 初始化旋转动画
     */
    private void initRotateAnimation() {
        upAnim = new RotateAnimation(0, -180,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        upAnim.setDuration(300);
        upAnim.setFillAfter(true);

        downAnim = new RotateAnimation(-180, -360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        downAnim.setDuration(300);
        downAnim.setFillAfter(true);

    }

    // 初始化头部，也就是下拉的时候显示出来的那部分
    private void initHeader() {

        headerView = View.inflate(getContext(), R.layout.layout_header, null);
        iv_arrow = (ImageView) headerView.findViewById(R.id.iv_arrow);
        tv_state = (TextView) headerView.findViewById(R.id.tv_state);
        tv_time = (TextView) headerView.findViewById(R.id.tv_time);
        pb_rotate = (ProgressBar) headerView.findViewById(R.id.pb_rotate);

        headerView.measure(0, 0); //  主动通知系统去测量
        headerHeight = headerView.getMeasuredHeight();
        headerView.setPadding(0, -headerHeight, 0, 0);

        addHeaderView(headerView);
        // 一定要在 setAdapter 之前调用 addHeaderView
        // 这个视图会在列表上方显示，不是下拉才显示。
    }

    private void initFooter() {
        footerView = View.inflate(getContext(), R.layout.layout_footer, null);

        footerView.measure(0, 0);
        footerHeight = footerView.getMeasuredHeight();
        footerView.setPadding(0, -footerHeight, 0, 0);
        // 注意设置的是 footerview 的 padding

        addFooterView(footerView);
    }

    // 监听事件
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                if(currentState == REFRESHING){
                    break;
                }

                int deltaY = (int) ev.getY() - downY;
                // y坐标变化了多少

                int newPaddingTop = -headerHeight + deltaY;
                // 假设: -60 + 20 = -40
                // 那么等于 0 的时候就是 header 完全展示出来了

                if (newPaddingTop > -headerHeight && getFirstVisiblePosition() == 0){
                    // 如果拉出来的正距离，大于header 的高度
                    headerView.setPadding(0, newPaddingTop, 0, 0);


                    if(newPaddingTop > 0 && currentState == PULL_REFRESH) {

                        currentState = RELEASH_REFRESH;
                        refreshHeaderView();

                    } else if (newPaddingTop < 0 && currentState == RELEASH_REFRESH) {

                        currentState = PULL_REFRESH;
                        refreshHeaderView();

                    }

                    return true; // 拦截 TouchMove 事件，不让 listview 处理
                }
//                break;
            case MotionEvent.ACTION_UP:
                // 松开手指有两种状态，一种是应该刷新，另一种是不用刷新
                if (currentState == PULL_REFRESH){   // 不用刷新
                    headerView.setPadding(0, -headerHeight, 0, 0);    // 隐藏 headerView
                } else if (currentState == RELEASH_REFRESH){   // 应该刷新
                    // header 要完全显示，刷新成功后消失
                    headerView.setPadding(0, 0, 0, 0);
                    currentState = REFRESHING;
                    refreshHeaderView();

//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            completeRefresh();
//                        }
//                    }, 3000);

                    if(listener != null){
                        listener.onPullRefresh();
                    }
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    public void refreshHeaderView(){
        switch (currentState){
            case PULL_REFRESH:
                tv_state.setText("下拉刷新");
                iv_arrow.startAnimation(downAnim);
                break;

            case RELEASH_REFRESH:
                tv_state.setText("松开刷新");
                iv_arrow.startAnimation(upAnim);
                break;

            case REFRESHING:
                tv_state.setText("刷新中");
                iv_arrow.clearAnimation();
                iv_arrow.setVisibility(View.INVISIBLE);
                pb_rotate.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * 完成刷新, 重置状态
     */
    public void completeRefresh(){
        if(isLoadingMore){
            // 重置 footerView 状态
            footerView.setPadding(0, -footerHeight,0,0);
            isLoadingMore = false;
        } else {
            // 重置 headerView 状态
            headerView.setPadding(0, -headerHeight,0,0);
            currentState = PULL_REFRESH;
            iv_arrow.setVisibility(View.VISIBLE);
            pb_rotate.setVisibility(View.INVISIBLE);
            tv_state.setText("下拉刷新");
            tv_time.setText("最后刷新: "+getCurrentTime());
        }
    }

    private String getCurrentTime(){
        SimpleDateFormat format =  new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    /**
     * SCROLL_STATE_IDLE         手指不在屏幕
     *             _TOUCH_SCROLL 按着滑动
     *             _FLING        快速滑动然后松开
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        if(scrollState == OnScrollListener.SCROLL_STATE_IDLE &&
                (getLastVisiblePosition() == getCount()-1) && !isLoadingMore){
             // 判断是否需要载入更多

             footerView.setPadding(0, 0, 0, 0);  // 显示出 footerView
             setSelection(getCount());           // 将对应位置的 item 放到屏幕顶端(试着去这样做, 但不是强制)
             if (listener!=null){
                 listener.onLoadingMore();
             }
        }

    }
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int
            totalItemCount) {

    }
}
