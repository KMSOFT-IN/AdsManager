package com.kmsoft.adsmanager;

import com.facebook.ads.AdSize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActivityConfig {


//    static String FB_BANNER_90 = "910721003297246_910723819963631";
    public static String FB_RECTANGLE;
    public static String FB_NATIVE;
    public static String FB_BANNER_ID;
    public static String FB_INTERSTITIAL;
    public static String FB_REWARD;
    public static String FB_REWARD_INTERSTITIAL;

    public static String GOOGLE_BANNER_ID;
    public static String GOOGLE_INTERSTITIAL;
    public static String GOOGLE_REWARD;
    public static String GOOGLE_REWARD_INTERSTITIAL;

    // TODO for highPriority set highNumber
    public static int fbPriority;
    public static int googlePriority;

    public static AdSize fbBannerSize;
    public static com.google.android.gms.ads.AdSize googleBannerSize;

    public static void setFbNative(String fbNative) {
        FB_NATIVE = fbNative;
    }

    public static void setFbRectangle(String fbRectangle) {
        FB_RECTANGLE = fbRectangle;
    }

    public static void setFbBannerId(String fbBannerId) {
        FB_BANNER_ID = fbBannerId;
    }

    public static void setFbInterstitial(String fbInterstitial) {
        FB_INTERSTITIAL = fbInterstitial;
    }

    public static void setFbReward(String fbReward) {
        FB_REWARD = fbReward;
    }

    public static void setFbRewardInterstitial(String fbRewardInterstitial) {
        FB_REWARD_INTERSTITIAL = fbRewardInterstitial;
    }

    public static void setGoogleBannerId(String googleBannerId) {
        GOOGLE_BANNER_ID = googleBannerId;
    }

    public static void setGoogleInterstitial(String googleInterstitial) {
        GOOGLE_INTERSTITIAL = googleInterstitial;
    }

    public static void setGoogleReward(String googleReward) {
        GOOGLE_REWARD = googleReward;
    }

    public static void setGoogleRewardInterstitial(String googleRewardInterstitial) {
        GOOGLE_REWARD_INTERSTITIAL = googleRewardInterstitial;
    }

    public static void setFbPriority(int fbPriority) {
        ActivityConfig.fbPriority = fbPriority;
    }

    public static void setGooglePriority(int googlePriority) {
        ActivityConfig.googlePriority = googlePriority;
    }

    public static void setFbBannerSize(AdSize fbBannerSize) {
        ActivityConfig.fbBannerSize = fbBannerSize;
    }

    public static void setGoogleBannerSize(com.google.android.gms.ads.AdSize googleBannerSize) {
        ActivityConfig.googleBannerSize = googleBannerSize;
    }

    public static List<Integer> sorting(){

        List<Integer> integerList = new ArrayList<>();

        integerList.add(ActivityConfig.fbPriority);
        integerList.add(ActivityConfig.googlePriority);

        Collections.sort(integerList, Collections.reverseOrder());

        return integerList;
    }
}