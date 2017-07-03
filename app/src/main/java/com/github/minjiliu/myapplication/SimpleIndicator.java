package com.github.minjiliu.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.github.minjiliu.imagebrowser.IIndicator;

/**
 * Created by mj on 17/7/3.
 */
public class SimpleIndicator implements IIndicator {

    private final Context context;
    private final View view;
    private final int[] images;
    private TextView textView;

    public SimpleIndicator(Context context, int[] images) {
        this.context = context;
        this.images = images;
        view = LayoutInflater.from(context).inflate(R.layout.indicator, null);
        init();
    }

    private void init() {
        textView = (TextView) view.findViewById(R.id.text_indicator);
        textView.setVisibility(View.GONE);
    }

    @Override
    public View getIndicatorView() {
        return view;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        textView.setVisibility(View.VISIBLE);
        textView.setText((position + 1) + " / " + images.length);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
