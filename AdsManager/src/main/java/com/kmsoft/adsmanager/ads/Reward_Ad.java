package com.kmsoft.adsmanager.ads;


import static com.kmsoft.adsmanager.Constants.Utils.sorting;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.kmsoft.adsmanager.Constants.Utils;
import com.kmsoft.adsmanager.listener.FbReward;
import com.kmsoft.adsmanager.listener.GoogleReward;
import com.kmsoft.adsmanager.listener.GoogleRewardItem;

import java.util.List;

public class Reward_Ad {

    RewardedVideoAd fbRewardedVideoAd;
    RewardedAd googleRewardVideoAd;
    Context context;
    FbReward fbReward;
    GoogleReward googleReward;

    public Reward_Ad(Context context, FbReward fbReward, GoogleReward googleReward) {
        this.context = context;
        this.fbReward = fbReward;
        this.googleReward = googleReward;
    }

    public void loadFbRewardVideo() {
        fbRewardedVideoAd = new RewardedVideoAd(context, Utils.FB_REWARD);

        RewardedVideoAdListener rewardedVideoAdListener = new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoCompleted() {
                fbReward.onRewardedVideoCompleted();
            }

            @Override
            public void onRewardedVideoClosed() {
                fbReward.onRewardedVideoClosed();
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                Toast.makeText(context, "Sorry, error on loading the ad. Try again!", Toast.LENGTH_SHORT).show();
                fbRewardedVideoAd = null;
            }

            @Override
            public void onAdLoaded(Ad ad) {

            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };

        fbRewardedVideoAd.loadAd(
                fbRewardedVideoAd.buildLoadAdConfig()
                        .withAdListener(rewardedVideoAdListener)
                        .build());

        loadGoogleRewardVideo();
    }

    private void showAd() {
        List<Integer> priorityList = sorting();

        for (int i = 0; i < priorityList.size(); i++) {

            if (priorityList.get(i) == Utils.fbPriority) {
                if (fbRewardedVideoAd != null) {
                    fbRewardedVideoAd.show();
                    Toast.makeText(context, "fb Ad show", Toast.LENGTH_SHORT).show();
                    break;
                }

            } else if (priorityList.get(i) == Utils.googlePriority) {
                if (googleRewardVideoAd != null) {
                    Toast.makeText(context, "google Ad show", Toast.LENGTH_SHORT).show();
                    googleRewardVideoAd.show(
                            (Activity) context, new OnUserEarnedRewardListener() {
                                @Override
                                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                    GoogleRewardItem googleRewardItem = new GoogleRewardItem();
                                    googleRewardItem.amount = rewardItem.getAmount();
                                    googleRewardItem.type = rewardItem.getType();
                                    googleReward.onUserEarnedReward(googleRewardItem);
                                }
                            });
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
                Utils.GOOGLE_REWARD,
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
