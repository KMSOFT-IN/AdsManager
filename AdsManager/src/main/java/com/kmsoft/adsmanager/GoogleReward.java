package com.kmsoft.adsmanager;

import androidx.annotation.NonNull;

public interface GoogleReward {

    void onUserEarnedReward(@NonNull GoogleRewardItem rewardItem);
}
