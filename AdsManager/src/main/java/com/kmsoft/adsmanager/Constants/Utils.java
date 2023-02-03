package com.kmsoft.adsmanager.Constants;

import android.content.Context;

import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utils {


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

    public static int fbBannerHeight;
    public static int googleBannerHeight;

    public static void initialize(Context context){
        AudienceNetworkAds.initialize(context);
    }

    public static void setTestMode(boolean result){
        AdSettings.setTestMode(result);
    }

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
        Utils.fbPriority = fbPriority;
    }

    public static void setGooglePriority(int googlePriority) {
        Utils.googlePriority = googlePriority;
    }

    public static void setGoogleBannerHeight(int googleBannerHeight) {

        if (googleBannerHeight < 0 && googleBannerHeight != -2 && googleBannerHeight != -4) {
            StringBuilder var5 = new StringBuilder();
            var5.append("Invalid height for AdSize: ");
            var5.append(googleBannerHeight);
            throw new IllegalArgumentException(var5.toString());
        }else {
            Utils.googleBannerHeight = googleBannerHeight;
        }
    }

    public static void setFbBannerHeight(int fbBannerHeight) {
        if (fbBannerHeight == 50 || fbBannerHeight == 90 || fbBannerHeight == 250){
            Utils.fbBannerHeight = fbBannerHeight;
        }else {
            throw new IllegalArgumentException("Can't create AdSize using this height. height should be 50,90,250");
        }

    }

    public static List<Integer> sorting(){

        List<Integer> integerList = new ArrayList<>();

        integerList.add(Utils.fbPriority);
        integerList.add(Utils.googlePriority);

        Collections.sort(integerList, Collections.reverseOrder());

        return integerList;
    }
}