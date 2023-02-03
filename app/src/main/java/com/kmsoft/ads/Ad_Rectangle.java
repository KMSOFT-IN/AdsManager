package com.kmsoft.ads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.kmsoft.adsmanager.RectangleAd;

public class Ad_Rectangle extends AppCompatActivity {

    RelativeLayout adContainer;
    RectangleAd rectangleAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_rectangle);

        adContainer = findViewById(R.id.ad_rectangle);

        rectangleAd = new RectangleAd(Ad_Rectangle.this,adContainer);
        rectangleAd.loadFbAd();
    }

}