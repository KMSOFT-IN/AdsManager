package com.sabithpkcmnr.facebookads;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.card.MaterialCardView;

public class ActivityHome extends AppCompatActivity {

    MaterialCardView cdBanner, cdInterstitial, cdRectangle, cdReward, cdNative,cdRewardIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cdBanner = findViewById(R.id.cdBanner);
        cdRectangle = findViewById(R.id.cdRectangle);
        cdInterstitial = findViewById(R.id.cdInterstitial);
        cdReward = findViewById(R.id.cdReward);
        cdNative = findViewById(R.id.cdNative);
        cdRewardIn = findViewById(R.id.cdRewardIn);

        cdBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityHome.this, Ad_Banner.class));
            }
        });

        cdRectangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityHome.this, Ad_Rectangle.class));
            }
        });

        cdInterstitial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityHome.this, Ad_Interstitial.class));
            }
        });

        cdReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ActivityHome.this, Ad_Reward.class);
                intent.putExtra("isVideo",true);
                startActivity(intent);
            }
        });

        cdNative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityHome.this, Ad_Native.class));
            }
        });

        cdRewardIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityHome.this, Ad_Reward.class);
                intent.putExtra("isVideo",false);
                startActivity(intent);
            }
        });
    }
}