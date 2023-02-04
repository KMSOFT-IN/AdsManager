package com.kmsoft.ads;

import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.kmsoft.adsmanager.ads.BannerAd;

public class Ad_Banner extends AppCompatActivity {

    RelativeLayout adContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_banner);

        adContainer = findViewById(R.id.ad_banner_50);

        ActivityBase.bannerAd.showBannerAd(adContainer,Ad_Banner.this);

    }

}