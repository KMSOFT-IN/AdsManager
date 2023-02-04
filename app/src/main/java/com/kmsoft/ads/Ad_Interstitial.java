package com.kmsoft.ads;

import static com.kmsoft.ads.ActivityBase.interstitialAd;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.kmsoft.adsmanager.ads.InterstitialAd;

public class Ad_Interstitial extends AppCompatActivity {

    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_interstitial);

        progress = findViewById(R.id.interstitial_progress);
        progress.setVisibility(View.GONE);

        interstitialAd.showInterstitialAd(this);

    }
}