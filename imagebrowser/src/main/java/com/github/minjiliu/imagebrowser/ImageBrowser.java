package com.github.minjiliu.imagebrowser;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by mj on 17/6/30.
 * 这是整个图片浏览器的容器
 */

public class ImageBrowser extends FrameLayout implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private InfinityAdapter infinityAdapter;
    private IIndicator indicator = IIndicator.NULL;

    public ImageBrowser(Context context) {
        this(context, null);
    }

    public ImageBrowser(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageBrowser(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            handleAttr(attrs);
        }

        inflate(context, R.layout.layout_image_browser, this);
        viewPager = (ViewPager) findViewById(R.id.view_view_pager);
        viewPager.addOnPageChangeListener(this);
    }

    private void handleAttr(AttributeSet attrs) {
        // TODO: 17/6/30 处理自定义属性
    }

    public void setAdapter(PagerAdapter adapter) {
        infinityAdapter = new InfinityAdapter(adapter, viewPager);
        viewPager.setAdapter(infinityAdapter);
    }

    public void addPageChangeListener(ViewPager.OnPageChangeListener listener) {
        viewPager.addOnPageChangeListener(listener);
    }

    public void setIndicator(IIndicator indicator) {
        // 移掉原先可能存在的View
        removeView(this.indicator.getIndicatorView());
        this.indicator = indicator;
        addView(this.indicator.getIndicatorView());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        indicator.onPageScrolled(infinityAdapter.calculateRealPosition(position), positionOffset,
                positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        indicator.onPageSelected(infinityAdapter.calculateRealPosition(position));
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        indicator.onPageScrollStateChanged(state);
    }

    /**
     * 将当前条目设置回中央位置
     */
    public void reset() {
        viewPager.setCurrentItem(InfinityAdapter.PRIMARY_POSITION);
    }
}
