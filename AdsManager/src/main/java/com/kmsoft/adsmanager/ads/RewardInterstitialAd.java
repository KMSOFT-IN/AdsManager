package com.kmsoft.adsmanager.ads;


import static com.kmsoft.adsmanager.Constants.Utils.sorting;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.RewardedInterstitialAd;
import com.facebook.ads.RewardedInterstitialAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback;
import com.kmsoft.adsmanager.Constants.Utils;
import com.kmsoft.adsmanager.listener.FbRewardInterstitial;
import com.kmsoft.adsmanager.listener.GoogleReward;
import com.kmsoft.adsmanager.listener.GoogleRewardItem;

import java.util.List;

public class RewardInterstitialAd {

    Context context;
    RewardedInterstitialAd fbRewardedInterstitialAd;
    com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd googleRewardInterstitialAd;
    FbRewardInterstitial fbRewardInterstitial;
    GoogleReward googleReward;

    public RewardInterstitialAd(Context context, FbRewardInterstitial fbRewardInterstitial, GoogleReward googleReward) {
        this.context = context;
        this.fbRewardInterstitial = fbRewardInterstitial;
        this.googleReward = googleReward;
    }

    public void loadFbRewardedInterstitial() {
        fbRewardedInterstitialAd = new RewardedInterstitialAd(context, Utils.FB_REWARD_INTERSTITIAL);
        RewardedInterstitialAdListener rewardedInterstitialAdListener = new RewardedInterstitialAdListener() {
            @Override
            public void onRewardedInterstitialCompleted() {
                fbRewardInterstitial.onRewardedInterstitialCompleted();
            }

            @Override
            public void onRewardedInterstitialClosed() {
                fbRewardInterstitial.onRewardedInterstitialClosed();
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                Toast.makeText(context, "Sorry, error on loading the ad. Try again!", Toast.LENGTH_SHORT).show();
                fbRewardedInterstitialAd = null;
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
                Utils.GOOGLE_REWARD_INTERSTITIAL,
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

            if (priorityList.get(i) == Utils.fbPriority) {
                if (fbRewardedInterstitialAd != null) {
                    fbRewardedInterstitialAd.show();
                    Toast.makeText(context, "fb Ad show", Toast.LENGTH_SHORT).show();
                    break;
                }

            } else if (priorityList.get(i) == Utils.googlePriority) {
                if (googleRewardInterstitialAd != null) {
                    Toast.makeText(context, "google Ad show", Toast.LENGTH_SHORT).show();
                    googleRewardInterstitialAd.show(
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
}
