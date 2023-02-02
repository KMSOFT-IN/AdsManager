package com.sabithpkcmnr.facebookads;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.RewardedInterstitialAdListener;
import com.facebook.ads.RewardedVideoAdListener;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.kmsoft.adsmanager.RewardInterstitialAd;
import com.kmsoft.adsmanager.Reward_Ad;

public class Ad_Reward extends AppCompatActivity implements RewardedVideoAdListener, OnUserEarnedRewardListener,RewardedInterstitialAdListener {

    boolean isVideo;
    Reward_Ad reward_ad;
    RewardInterstitialAd rewardInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_reward);

        isVideo = getIntent().getBooleanExtra("isVideo", true);

        reward_ad = new Reward_Ad(Ad_Reward.this,this, this);
        rewardInterstitialAd = new RewardInterstitialAd(Ad_Reward.this,this, this);

        if (isVideo) {
            reward_ad.loadFbRewardVideo();
        } else {
            rewardInterstitialAd.loadFbRewardedInterstitial();
        }

    }

    @Override
    public void onRewardedVideoCompleted() {
        Toast.makeText(this, "complete", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoClosed() {
        Toast.makeText(this, "closed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Ad ad, AdError adError) {
        Toast.makeText(this, "Sorry, error on loading the ad. Try again!", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
        int rewardAmount = rewardItem.getAmount();
        String rewardType = rewardItem.getType();
    }

    @Override
    public void onRewardedInterstitialCompleted() {

    }

    @Override
    public void onRewardedInterstitialClosed() {

    }
}