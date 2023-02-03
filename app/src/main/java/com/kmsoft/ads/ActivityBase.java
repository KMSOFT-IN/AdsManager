package com.kmsoft.ads;

import android.app.Application;

import com.kmsoft.adsmanager.Constants.Utils;

public class ActivityBase extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        AudienceNetworkAds.initialize(this);
        Utils.initialize(this);

        //This will make the ad run on the test device, let's say your Android AVD emulator
        if (BuildConfig.DEBUG){
            Utils.setTestMode(true);
        }

        // TODO set size
        Utils.setFbBannerHeight(50);
        Utils.setGoogleBannerHeight(50);
        Utils.setUnityBannerHeight(50);
        Utils.setUnityBannerWidth(320);

        // TODO set Priority
        Utils.setFbPriority(3);
        Utils.setGooglePriority(2);
        Utils.setUnityPriority(4);

        // TODO set Banner Ad Data

        Utils.setFbBannerId("910721003297246_910723819963631");
        Utils.setGoogleBannerId("ca-app-pub-3940256099942544/6300978111");

        // TODO set Interstitial Ad data
        Utils.setGoogleInterstitial("ca-app-pub-3940256099942544/1033173712");
        Utils.setFbInterstitial("910721003297246_910776049958408");

        // TODO set Reward Video Ad data
        Utils.setGoogleReward("ca-app-pub-3940256099942544/5224354917");
        Utils.setFbReward("910721003297246_910776606625019");

        // TODO set Reward Interstitial Ad data
        Utils.setGoogleRewardInterstitial("ca-app-pub-3940256099942544/5354046379");
        Utils.setFbRewardInterstitial("910721003297246_910785149957498");


        Utils.setFbRectangle("910721003297246_910775713291775");

//        Utils.setFbNative("910721003297246_910775466625133");

        // TODO set Unity GameId
        Utils.setUnityGameId("5147445");
        Utils.setUnityBannerId("test123");
        Utils.setUnityInterstitialId("Interstitial123");
        Utils.setUnityRewardId("Reward123");

    }
}