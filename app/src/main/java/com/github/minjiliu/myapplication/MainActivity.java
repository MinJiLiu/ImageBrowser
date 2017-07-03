package com.github.minjiliu.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.minjiliu.imagebrowser.ImageBrowser;

public class MainActivity extends AppCompatActivity {

    private static final int[] images = {R.mipmap.image0, R.mipmap.image1, R.mipmap.image2, R
            .mipmap.image3, R.mipmap.image4,};

    private ImageBrowser imageBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageBrowser = (ImageBrowser) findViewById(R.id.view_image_browser);
        imageBrowser.setAdapter(new SimpleAdapter(images));
        imageBrowser.setIndicator(new SimpleIndicator(this, images));
    }

    @Override
    protected void onResume() {
        super.onResume();
        imageBrowser.reset();
    }
}
