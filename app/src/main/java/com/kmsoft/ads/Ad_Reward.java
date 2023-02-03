package com.kmsoft.ads;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.kmsoft.adsmanager.listener.FbReward;
import com.kmsoft.adsmanager.listener.FbRewardInterstitial;
import com.kmsoft.adsmanager.listener.GoogleReward;
import com.kmsoft.adsmanager.listener.GoogleRewardItem;
import com.kmsoft.adsmanager.ads.RewardInterstitialAd;
import com.kmsoft.adsmanager.ads.Reward_Ad;

public class Ad_Reward extends AppCompatActivity implements FbReward, GoogleReward, FbRewardInterstitial {

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
    public void onRewardedInterstitialCompleted() {
        Toast.makeText(this, "complete", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedInterstitialClosed() {
        Toast.makeText(this, "closed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUserEarnedReward(@NonNull GoogleRewardItem rewardItem) {
        int rewardAmount = rewardItem.getAmount();
        String rewardType = rewardItem.getType();

        Toast.makeText(this, "amount : " + rewardAmount, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "type : " + rewardType, Toast.LENGTH_SHORT).show();
    }
}