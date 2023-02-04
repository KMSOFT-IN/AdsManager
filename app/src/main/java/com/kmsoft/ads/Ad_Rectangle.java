package com.kmsoft.ads;

import static com.kmsoft.ads.ActivityBase.rectangleAd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.kmsoft.adsmanager.ads.RectangleAd;

public class Ad_Rectangle extends AppCompatActivity {

    RelativeLayout adContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_rectangle);

        adContainer = findViewById(R.id.ad_rectangle);

        rectangleAd.showRectangleAd(adContainer,this);
    }

}