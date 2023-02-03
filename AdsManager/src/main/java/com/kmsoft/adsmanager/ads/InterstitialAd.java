package com.kmsoft.adsmanager.ads;

import static com.kmsoft.adsmanager.Constants.Utils.sorting;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.ads.AbstractAdListener;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.kmsoft.adsmanager.Constants.Utils;

import java.util.List;

public class InterstitialAd {

    com.facebook.ads.InterstitialAd FbInterstitialAd;
    com.google.android.gms.ads.interstitial.InterstitialAd googleInterstitialAd;
    Context context;

    public InterstitialAd(Context context) {
        this.context = context;
    }

    private void GoogleInterstitialAd() {

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        com.google.android.gms.ads.interstitial.InterstitialAd.load(
                context,
                Utils.GOOGLE_INTERSTITIAL,
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                        googleInterstitialAd = interstitialAd;
                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        Log.d("TAG", "The ad was dismissed.");
                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                                        Log.d("TAG", "The ad failed to show.");
                                    }

                                    @Override
                                    public void onAdShowedFullScreenContent() {
                                        // Called when fullscreen content is shown.
                                        Log.d("TAG", "The ad was shown.");
                                    }
                                });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        String error = String.format("domain: %s, code: %d, message: %s", loadAdError.getDomain(), loadAdError.getCode(), loadAdError.getMessage());
                        Toast.makeText(context, "onAdFailedToLoad() with error: " + error, Toast.LENGTH_SHORT).show();
                    }
                });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showAd();
            }
        }, 2500);
    }

    private void showAd() {

        List<Integer> priorityList = sorting();

        for (int i = 0; i < priorityList.size(); i++) {

            if (priorityList.get(i) == Utils.fbPriority){
                if (FbInterstitialAd != null) {
                    FbInterstitialAd.show();
                    Toast.makeText(context, "fb Ad show", Toast.LENGTH_SHORT).show();
                    break;
                }
            } else if (priorityList.get(i) == Utils.googlePriority){
                if (googleInterstitialAd != null) {
                    googleInterstitialAd.show((Activity) context);
                    Toast.makeText(context, "google Ad show", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }
    }

    public void FbInterstitialAd() {
        FbInterstitialAd = new com.facebook.ads.InterstitialAd(context, Utils.FB_INTERSTITIAL);
        AbstractAdListener adListener = new AbstractAdListener() {
            @Override
            public void onError(Ad ad, AdError error) {
                Toast.makeText(context, "Error loading ad: " + error.getErrorMessage(), Toast.LENGTH_SHORT).show();
                super.onError(ad, error);
                FbInterstitialAd = null;
            }

            @Override
            public void onAdLoaded(Ad ad) {
                super.onAdLoaded(ad);

            }

            @Override
            public void onAdClicked(Ad ad) {
                super.onAdClicked(ad);
            }

            @Override
            public void onInterstitialDisplayed(Ad ad) {
                super.onInterstitialDisplayed(ad);
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                super.onInterstitialDismissed(ad);
            }
        };
        com.facebook.ads.InterstitialAd.InterstitialLoadAdConfig interstitialLoadAdConfig = FbInterstitialAd.buildLoadAdConfig()
                .withAdListener(adListener)
                .build();
        FbInterstitialAd.loadAd(interstitialLoadAdConfig);

        GoogleInterstitialAd();
    }
}
