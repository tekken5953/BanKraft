package com.example.bankraft.Notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bankraft.MainPage.HomeActivity;
import com.example.bankraft.databinding.NotificationBinding;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    NotificationBinding binding;
    NotificationRecyclerAdapter mAdapter;
    ArrayList<NotificationRecyclerItem> mItem = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = NotificationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        RecyclerView recyclerView = binding.notifyRecyclerView;
        TextView nothing = binding.notificationNothing;

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        mAdapter = new NotificationRecyclerAdapter(mItem);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(NotificationActivity.this));

        Button btn1 = binding.notifyBtn1; // 이체
        Button btn2 = binding.notifyBtn2; // 투자
        Button btn3 = binding.notifyBtn3; // 환율
        Button btn4 = binding.notifyBtn4; // 이벤트

        binding.notifyBack.setOnClickListener(view1 -> {
            Intent intent = new Intent(NotificationActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });

        // 탭 클릭 이벤트
        btn1.setOnClickListener(view1 -> {
            {
                btn1.setTextColor(Color.parseColor("#87ef83"));
                btn2.setTextColor(Color.parseColor("#FFFFFF"));
                btn3.setTextColor(Color.parseColor("#FFFFFF"));
                btn4.setTextColor(Color.parseColor("#FFFFFF"));
                mItem.clear();
                nothing.setVisibility(View.VISIBLE);
                mAdapter.notifyDataSetChanged();
            }
        });

        btn2.setOnClickListener(view1 -> {
            {
                btn2.setTextColor(Color.parseColor("#87ef83"));
                btn1.setTextColor(Color.parseColor("#FFFFFF"));
                btn3.setTextColor(Color.parseColor("#FFFFFF"));
                btn4.setTextColor(Color.parseColor("#FFFFFF"));
                mItem.clear();
                // 알람 아이템 추가
                addItem("입금", "테스트 코드1", "+150,000", "2020.12.10");
                addItem("출금", "테스트 코드2", "-50,000", "2020.12.10");
                addItem("입금", "테스트 코드3", "+70,000", "2020.12.10");
                nothing.setVisibility(View.GONE);
                mAdapter.notifyDataSetChanged();
            }
        });

        btn3.setOnClickListener(view1 -> {
            {
                btn3.setTextColor(Color.parseColor("#87ef83"));
                btn1.setTextColor(Color.parseColor("#FFFFFF"));
                btn2.setTextColor(Color.parseColor("#FFFFFF"));
                btn4.setTextColor(Color.parseColor("#FFFFFF"));
                mItem.clear();
                nothing.setVisibility(View.VISIBLE);
                mAdapter.notifyDataSetChanged();
            }
        });

        btn4.setOnClickListener(view1 -> {
            {
                btn4.setTextColor(Color.parseColor("#87ef83"));
                btn1.setTextColor(Color.parseColor("#FFFFFF"));
                btn2.setTextColor(Color.parseColor("#FFFFFF"));
                btn3.setTextColor(Color.parseColor("#FFFFFF"));
                mItem.clear();
                nothing.setVisibility(View.VISIBLE);
                mAdapter.notifyDataSetChanged();
            }
        });

    }

    //RecyclerView 아이템 추가
    public void addItem(String io, String content, String price, String date) {
        NotificationRecyclerItem item = new NotificationRecyclerItem(io, content, price, date);
        item.setIo(io);
        item.setContent(content);
        item.setPrice(price);
        item.setDate(date);
        mItem.add(item);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(NotificationActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

}