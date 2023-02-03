package com.kmsoft.adsmanager.ads;

import static com.kmsoft.adsmanager.Constants.Utils.sorting;

import android.content.Context;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.kmsoft.adsmanager.Constants.Utils;

import java.util.List;

public class BannerAd {

    Ad fbAd;
    AdView fbAdView;
    com.google.android.gms.ads.AdView googleAdView;
    boolean isGoogleAdLoaded = false;
    Context context;
    ViewGroup adContainer;

    public BannerAd(Context context, ViewGroup adContainer) {
        this.context = context;
        this.adContainer = adContainer;
    }

    private void loadGoogleAd() {
        googleAdView = new com.google.android.gms.ads.AdView(context);
        googleAdView.setAdUnitId(Utils.GOOGLE_BANNER_ID);
        googleAdView.setAdSize(new com.google.android.gms.ads.AdSize(-1,Utils.googleBannerHeight));
        AdRequest adRequest = new AdRequest.Builder().build();
        googleAdView.setAdListener(new com.google.android.gms.ads.AdListener() {
            @Override
            public void onAdLoaded() {
                isGoogleAdLoaded = true;
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                isGoogleAdLoaded = false;
            }
        });
        googleAdView.loadAd(adRequest);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showAd();
            }
        }, 2500);
    }

    public void loadFbAd() {
        fbAdView = new AdView(context, Utils.FB_BANNER_ID, new AdSize(-1,Utils.fbBannerHeight));
        AdListener fbAdListener = new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                fbAd = null;
            }

            @Override
            public void onAdLoaded(Ad ad) {
                fbAd = ad;
            }

            @Override
            public void onAdClicked(Ad ad) {
            }

            @Override
            public void onLoggingImpression(Ad ad) {
            }
        };
        AdView.AdViewLoadConfig loadAdConfig = fbAdView.buildLoadAdConfig()
                .withAdListener(fbAdListener)
                .build();
        fbAdView.loadAd(loadAdConfig);

        loadGoogleAd();
    }

    private void showAd() {

        List<Integer> priorityList = sorting();

        for (int i = 0; i < priorityList.size(); i++) {

            if (priorityList.get(i) == Utils.fbPriority) {
                if (fbAd != null) {
                    adContainer.addView(fbAdView);
                    Toast.makeText(context, "fb Ad show", Toast.LENGTH_SHORT).show();
                    break;
                }

            } else if (priorityList.get(i) == Utils.googlePriority) {
                if (isGoogleAdLoaded) {
                    adContainer.addView(googleAdView);
                    Toast.makeText(context, "google Ad show", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }

    }
}
