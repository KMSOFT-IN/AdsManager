package com.kmsoft.adsmanager;


import static com.kmsoft.adsmanager.ActivityConfig.sorting;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import java.util.List;

public class Reward_Ad {

    RewardedVideoAd fbRewardedVideoAd;
    RewardedAd googleRewardVideoAd;
    Context context;
    RewardedVideoAdListener rewardedVideoAdListener;
    OnUserEarnedRewardListener onUserEarnedRewardListener;

    public Reward_Ad(Context context, RewardedVideoAdListener rewardedVideoAdListener, OnUserEarnedRewardListener onUserEarnedRewardListener) {
        this.context = context;
        this.rewardedVideoAdListener = rewardedVideoAdListener;
        this.onUserEarnedRewardListener = onUserEarnedRewardListener;
    }

    public void loadFbRewardVideo() {
        fbRewardedVideoAd = new RewardedVideoAd(context, ActivityConfig.FB_REWARD);
        fbRewardedVideoAd.loadAd(
                fbRewardedVideoAd.buildLoadAdConfig()
                        .withAdListener(rewardedVideoAdListener)
                        .build());

        loadGoogleRewardVideo();
    }

    private void showAd() {


        List<Integer> priorityList = sorting();

        for (int i = 0; i < priorityList.size(); i++) {

            if (priorityList.get(i) == ActivityConfig.fbPriority) {
                if (fbRewardedVideoAd != null) {
                    fbRewardedVideoAd.show();
                    Toast.makeText(context, "fb Ad show", Toast.LENGTH_SHORT).show();
                    break;
                }

            } else if (priorityList.get(i) == ActivityConfig.googlePriority) {
                if (googleRewardVideoAd != null) {
                    Toast.makeText(context, "google Ad show", Toast.LENGTH_SHORT).show();
                    googleRewardVideoAd.show(
                            (Activity) context, onUserEarnedRewardListener);
                    break;
                }

            }
        }
    }

    private void loadGoogleRewardVideo() {

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(
                context,
                ActivityConfig.GOOGLE_REWARD,
                adRequest,
                new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        googleRewardVideoAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        googleRewardVideoAd = rewardedAd;
                    }
                });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showAd();
            }
        }, 3000);
    }


}
