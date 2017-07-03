package com.github.minjiliu.imagebrowser;

import android.database.DataSetObserver;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mj on 17/6/30.
 * 对ViewPager进行无限化
 */

public class InfinityAdapter extends PagerAdapter {

    public static final int MAX_COUNT = 30000;
    public static final int PRIMARY_POSITION = MAX_COUNT / 3;

    private PagerAdapter pagerAdapter;
    private ViewPager viewPager;

    public InfinityAdapter(PagerAdapter pagerAdapter, ViewPager viewPager) {
        this.pagerAdapter = pagerAdapter;
        this.viewPager = viewPager;
    }

    @Override
    public int getCount() {
        return pagerAdapter.getCount() == 0 ? 0 : MAX_COUNT;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.i("ImageBrowser", "position = " + position);
        return pagerAdapter.instantiateItem(container, calculateRealPosition(position));
    }

    /**
     * 计算真实Pager的位置
     */
    public int calculateRealPosition(int rawPosition) {
        int result;
        if (rawPosition < PRIMARY_POSITION) {
            int offset = PRIMARY_POSITION - rawPosition;
            offset %= pagerAdapter.getCount();
            result = offset == 0 ? 0 : pagerAdapter.getCount() - offset;
        } else {
            result = (rawPosition - PRIMARY_POSITION) % pagerAdapter.getCount();
        }
        return result;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        pagerAdapter.destroyItem(container, calculateRealPosition(position), object);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        super.registerDataSetObserver(observer);
        pagerAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                InfinityAdapter.this.notifyDataSetChanged();
            }

            @Override
            public void onInvalidated() {
                InfinityAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        super.unregisterDataSetObserver(observer);
    }
}
