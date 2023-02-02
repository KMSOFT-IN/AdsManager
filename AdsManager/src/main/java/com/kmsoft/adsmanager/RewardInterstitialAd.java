package com.kmsoft.adsmanager;


import static com.kmsoft.adsmanager.ActivityConfig.sorting;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.facebook.ads.RewardedInterstitialAd;
import com.facebook.ads.RewardedInterstitialAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback;

import java.util.List;

public class RewardInterstitialAd {

    Context context;
    RewardedInterstitialAd fbRewardedInterstitialAd;
    com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd googleRewardInterstitialAd;
    RewardedInterstitialAdListener rewardedInterstitialAdListener;
    OnUserEarnedRewardListener onUserEarnedRewardListener;

    public RewardInterstitialAd(Context context, RewardedInterstitialAdListener rewardedInterstitialAdListener, OnUserEarnedRewardListener onUserEarnedRewardListener) {
        this.context = context;
        this.rewardedInterstitialAdListener = rewardedInterstitialAdListener;
        this.onUserEarnedRewardListener = onUserEarnedRewardListener;
    }

    public void loadFbRewardedInterstitial() {
        fbRewardedInterstitialAd = new RewardedInterstitialAd(context, ActivityConfig.FB_REWARD_INTERSTITIAL);
        fbRewardedInterstitialAd.loadAd(
                fbRewardedInterstitialAd.buildLoadAdConfig()
                        .withAdListener(rewardedInterstitialAdListener)
                        .build());

        loadGoogleRewardInterstitial();
    }

    private void loadGoogleRewardInterstitial() {
        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        // Use the test ad unit ID to load an ad.
        com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd.load(
                context,
                ActivityConfig.GOOGLE_REWARD_INTERSTITIAL,
                adRequest,
                new RewardedInterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd ad) {
                        googleRewardInterstitialAd = ad;
                    }

                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {

                        // Handle the error.
                        googleRewardInterstitialAd = null;
                    }
                });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showAd();
            }
        }, 3000);

    }

    private void showAd() {

        List<Integer> priorityList = sorting();

        for (int i = 0; i < priorityList.size(); i++) {

            if (priorityList.get(i) == ActivityConfig.fbPriority) {
                if (fbRewardedInterstitialAd != null) {
                    fbRewardedInterstitialAd.show();
                    Toast.makeText(context, "fb Ad show", Toast.LENGTH_SHORT).show();
                    break;
                }

            } else if (priorityList.get(i) == ActivityConfig.googlePriority) {
                if (googleRewardInterstitialAd != null) {
                    Toast.makeText(context, "google Ad show", Toast.LENGTH_SHORT).show();
                    googleRewardInterstitialAd.show(
                            (Activity) context, onUserEarnedRewardListener);
                    break;
                }

            }
        }
    }
}
