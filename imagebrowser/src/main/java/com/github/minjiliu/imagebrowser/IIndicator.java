package com.github.minjiliu.imagebrowser;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by mj on 17/6/30.
 * 用来做图片浏览器的指示器
 */
public interface IIndicator extends ViewPager.OnPageChangeListener {

    IIndicator NULL = new IIndicator() {

        @Override
        public View getIndicatorView() {
            return null;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /**
     * 获取图片浏览器的指示器
     */
    View getIndicatorView();

}
