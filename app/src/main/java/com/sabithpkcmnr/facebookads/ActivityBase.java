package com.sabithpkcmnr.facebookads;

import android.app.Application;

import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.AudienceNetworkAds;
import com.kmsoft.adsmanager.ActivityConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActivityBase extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AudienceNetworkAds.initialize(this);

        //This will make the ad run on the test device, let's say your Android AVD emulator
        if (BuildConfig.DEBUG){
            AdSettings.setTestMode(true);
        }

        // TODO set Priority
        ActivityConfig.setFbPriority(2);
        ActivityConfig.setGooglePriority(3);

        // TODO set Banner Ad Data
        ActivityConfig.setFbBannerSize(AdSize.BANNER_HEIGHT_50);
        ActivityConfig.setGoogleBannerSize(com.google.android.gms.ads.AdSize.BANNER);
        ActivityConfig.setFbBannerId("910721003297246_910723819963631");
        ActivityConfig.setGoogleBannerId("ca-app-pub-3940256099942544/6300978111");

        // TODO set Interstitial Ad data
        ActivityConfig.setGoogleInterstitial("ca-app-pub-3940256099942544/1033173712");
        ActivityConfig.setFbInterstitial("910721003297246_910776049958408");

        // TODO set Reward Video Ad data
        ActivityConfig.setGoogleReward("ca-app-pub-3940256099942544/5224354917");
        ActivityConfig.setFbReward("910721003297246_910776606625019");

        // TODO set Reward Interstitial Ad data
        ActivityConfig.setGoogleRewardInterstitial("ca-app-pub-3940256099942544/5354046379");
        ActivityConfig.setFbRewardInterstitial("910721003297246_910785149957498");

        ActivityConfig.setFbRectangle("910721003297246_910775713291775");

        ActivityConfig.setFbNative("910721003297246_910775466625133");

    }
}