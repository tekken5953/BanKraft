package com.example.bankraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.bankraft.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    Boolean isMenuShow = false;
    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.tradingList.setOnClickListener(view1 -> {
            Intent intent = new Intent(HomeActivity.this, TradingListActivity.class);
            startActivity(intent);
            finish();
        });

        binding.notification.setOnClickListener(view1 -> {
            Intent intent = new Intent(HomeActivity.this, NotificationActivity.class);
            startActivity(intent);
            finish();
        });

        binding.goTradingTx.setOnClickListener(view1 -> {
            Intent intent = new Intent(HomeActivity.this, TradingPage.class);
            startActivity(intent);
            finish();
        });

        binding.goSidemenu.setOnClickListener(view1 -> {
            SideBarView sideBarView = new SideBarView(HomeActivity.this);
            binding.viewSlidebar.addView(sideBarView);

            binding.flSlide.setOnClickListener(view2 -> {
                sideBarView.setEventListener(this::closeMenu);
                showMenu();
            });
        });
    }

    public void closeMenu() {
        isMenuShow = false;
        Animation slide = AnimationUtils.loadAnimation(HomeActivity.this, R.anim.sidebar_hidden);
        binding.viewSlidebar.startAnimation(slide);
        new Handler().postDelayed(() -> {
            binding.flSlide.setVisibility(View.GONE);
            binding.flSlide.setEnabled(false);
            binding.idMain.setEnabled(true);
        }, 180);
    }

    public void showMenu() {
        isMenuShow = true;
        Animation slide = AnimationUtils.loadAnimation(HomeActivity.this, R.anim.sidebar_show);
        binding.viewSlidebar.startAnimation(slide);
        binding.flSlide.setVisibility(View.VISIBLE);
        binding.flSlide.setEnabled(true);
        binding.idMain.setEnabled(false);
    }

}