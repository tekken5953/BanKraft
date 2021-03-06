package com.example.bankraft.MainPage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bankraft.Login.LoginActivity;
import com.example.bankraft.Notification.NotificationActivity;
import com.example.bankraft.R;
import com.example.bankraft.SearchActivity;
import com.example.bankraft.SharedPreferenceManager;
import com.example.bankraft.SideBarView;
import com.example.bankraft.TradingListActivity;
import com.example.bankraft.DoTradingPage.TradingPage;
import com.example.bankraft.databinding.HomeActivityBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    Boolean isMenuShow = false;
    HomeActivityBinding binding;
    HomePannelRecyclerAdapter mAdapter;
    ArrayList<HomePannelRecyclerItem> mItem = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HomeActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //TODO 바텀 메뉴 구성
        BottomNavigationView bottomNavigationView = binding.bottomnavigationView;
        bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);

        //유저 이름 받아오기
        try {
            binding.homeUserName.setText(SharedPreferenceManager.getString(HomeActivity.this, "user_name") + " 님");
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(HomeActivity.this, "세션만료. 재 로그인 필요.", Toast.LENGTH_SHORT).show();
        }

        //사이드 메뉴 열기
        binding.goSidemenu.setOnClickListener(view1 -> {
            SideBarView sideBarView = new SideBarView(HomeActivity.this);
            binding.viewSlidebar.addView(sideBarView);

            sideBarView.setEventListener(new SideBarView.EventListener() {
                @Override
                public void btnCancel() {
                    closeMenu();
                }

                @Override
                public void logOut() {
                    Toast.makeText(HomeActivity.this, "로그아웃", Toast.LENGTH_SHORT).show();
                    //TODO 로그아웃 다이얼로그로 로그아웃
                    //로그아웃 시 저장된 이름과 시간 삭제
                    SharedPreferenceManager.removeKey(HomeActivity.this, "user_name");
                    SharedPreferenceManager.removeKey(HomeActivity.this, "enter_clock");
                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            showMenu();
        });

        //스위치 체크별 잔액확인
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switch1 = binding.switch1;
        TextView currentCash = binding.currentCash;

        switch1.setOnCheckedChangeListener((compoundButton, b) -> {
            if (switch1.isChecked()) {
                //TODO 현재잔액 계좌정보에서 받아오기
                currentCash.setText("현재 잔액(원)");
            } else {
                currentCash.setText("잔액 숨기기");
            }
        });

        //홈 메뉴 판넬 리사이클러뷰
        RecyclerView recyclerView = binding.homeRecyclerView;
        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        mAdapter = new HomePannelRecyclerAdapter(mItem);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false));
        //리사이클러뷰에 아이템 추가
        final int[] img = { R.drawable.dollor, R.drawable.airplain, R.drawable.money,
                R.drawable.paper, R.drawable.piggy,R.drawable.secures};
        for (int i = 0; i < 6; i++){
            addItem(ResourcesCompat.getDrawable(getResources(),img[i],null),"메뉴"+i);
            mAdapter.notifyDataSetChanged();
        }

        //액티비티 이동
        binding.fab.setOnClickListener(view1 -> {
            Intent intent = new Intent(HomeActivity.this, TradingPage.class);
            startActivity(intent);
            finish();
        });

        binding.tradingList.setOnClickListener(view1 -> {
            Intent intent = new Intent(HomeActivity.this, TradingListActivity.class);
            startActivity(intent);
            finish();
        });

        binding.homeSearch.setOnClickListener(view1 -> {
            Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
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

        binding.newAccount.setOnClickListener(view1 -> {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final View v = LayoutInflater.from(HomeActivity.this).inflate(R.layout.manage_account, null, false);
            builder.setView(v);
            final AlertDialog alertDialog = builder.create();
            final ImageView back = v.findViewById(R.id.manage_back);
            final TextView user_name = v.findViewById(R.id.manage_user_name);
            user_name.setText(SharedPreferenceManager.getString(this, "user_name" ) + "님의 계좌");
            alertDialog.setCanceledOnTouchOutside(false);
            back.setOnClickListener(view2 -> {
                alertDialog.dismiss();
            });
            alertDialog.show();
        });
    }

    //RecyclerView 아이템 추가
    public void addItem(Drawable img, String content) {
        HomePannelRecyclerItem item = new HomePannelRecyclerItem(img, content);
        item.setImg(img);
        item.setHome_context(content);
        mItem.add(item);
    }

    //메뉴 닫기
    public void closeMenu() {
        isMenuShow = false;
        Animation slide = AnimationUtils.loadAnimation(HomeActivity.this, R.anim.sidebar_hidden);
        binding.viewSlidebar.startAnimation(slide);
        new Handler().postDelayed(() -> {
            binding.flSlide.setVisibility(View.GONE);
            binding.flSlide.setEnabled(false);
            binding.idMain.setEnabled(true);
            binding.goSidemenu.setEnabled(true);
            binding.tradingList.setEnabled(true);
            binding.goTradingTx.setEnabled(true);
            binding.newAccount.setEnabled(true);
            binding.switch1.setEnabled(true);
            binding.homeSearch.setEnabled(true);
            binding.notification.setEnabled(true);
            binding.homeRecyclerView.setEnabled(true);
            binding.bottomAppbar.setVisibility(View.VISIBLE);
            binding.fab.setVisibility(View.VISIBLE);
            binding.bottomAppbar.setEnabled(true);
            binding.fab.setEnabled(true);
            binding.bottomnavigationView.getMenu().getItem(2).setEnabled(false);
        }, 250);
    }

    //메뉴 보여주기
    public void showMenu() {
        isMenuShow = true;
        Animation slide = AnimationUtils.loadAnimation(HomeActivity.this, R.anim.sidebar_show);
        binding.viewSlidebar.startAnimation(slide);
        binding.flSlide.setVisibility(View.VISIBLE);
        binding.flSlide.setEnabled(true);
        binding.idMain.setEnabled(false);
        binding.goSidemenu.setEnabled(false);
        binding.tradingList.setEnabled(false);
        binding.goTradingTx.setEnabled(false);
        binding.newAccount.setEnabled(false);
        binding.switch1.setEnabled(false);
        binding.homeSearch.setEnabled(false);
        binding.notification.setEnabled(false);
        binding.homeRecyclerView.setEnabled(false);
        binding.bottomAppbar.setVisibility(View.GONE);
        binding.fab.setVisibility(View.GONE);
        binding.bottomAppbar.setEnabled(false);
        binding.fab.setEnabled(false);
    }

}