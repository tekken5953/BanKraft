package com.example.bankraft.DoTradingPage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankraft.MainPage.HomeActivity;
import com.example.bankraft.Notification.NotificationActivity;
import com.example.bankraft.Notification.NotificationRecyclerAdapter;
import com.example.bankraft.Notification.NotificationRecyclerItem;
import com.example.bankraft.R;
import com.example.bankraft.databinding.DoTradingBinding;

import java.util.ArrayList;

public class TradingPage extends AppCompatActivity {

    DoTradingBinding binding;
    TradingListAdapter mAdapter;
    ArrayList<TradingListItem> mItem = new ArrayList<>();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TradingPage.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DoTradingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        TextView tx1 = binding.doTradingTx1;
        TextView tx2 = binding.doTradingTx2;
        TextView tx3 = binding.doTradingTx3;

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        RecyclerView recyclerView = binding.doTradingListview;
        mAdapter = new TradingListAdapter(mItem);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(TradingPage.this));

        addItem("test1_최근 (0000-00-00)", "account1");
        addItem("test2_최근 (0000-00-00)", "account2");
        addItem("test3_최근 (0000-00-00)", "account3");
        addItem("test4_최근 (0000-00-00)", "account4");
        mAdapter.notifyDataSetChanged();

        tx1.setOnClickListener(view1 -> {
            tx1.setTextColor(Color.parseColor("#87ef83"));
            tx2.setTextColor(Color.parseColor("#FFFFFF"));
            tx3.setTextColor(Color.parseColor("#FFFFFF"));
            mItem.clear();
            addItem("test1_최근 (0000-00-00)", "account1");
            addItem("test2_최근 (0000-00-00)", "account2");
            addItem("test3_최근 (0000-00-00)", "account3");
            addItem("test4_최근 (0000-00-00)", "account4");
            mAdapter.notifyDataSetChanged();
        });

        tx2.setOnClickListener(view1 -> {
            tx2.setTextColor(Color.parseColor("#87ef83"));
            tx1.setTextColor(Color.parseColor("#FFFFFF"));
            tx3.setTextColor(Color.parseColor("#FFFFFF"));
            mItem.clear();
            addItem("test1_자주 (0000-00-00)", "account1");
            addItem("test2_자주 (0000-00-00)", "account2");
            addItem("test3_자주 (0000-00-00)", "account3");
            addItem("test4_자주 (0000-00-00)", "account4");
            mAdapter.notifyDataSetChanged();
        });

        tx3.setOnClickListener(view1 -> {
            tx3.setTextColor(Color.parseColor("#87ef83"));
            tx1.setTextColor(Color.parseColor("#FFFFFF"));
            tx2.setTextColor(Color.parseColor("#FFFFFF"));
            mItem.clear();
            addItem("test1_유저 (0000-00-00)", "account1");
            addItem("test2_유저 (0000-00-00)", "account2");
            addItem("test3_유저 (0000-00-00)", "account3");
            addItem("test4_유저 (0000-00-00)", "account4");
            mAdapter.notifyDataSetChanged();
        });

        binding.tradingCancel.setOnClickListener(view1 -> {
            Intent intent = new Intent(TradingPage.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });
    }

    //RecyclerView 아이템 추가
    public void addItem(String person, String account) {
        TradingListItem item = new TradingListItem(person, account);
        item.setPerson(person);
        item.setAccount(account);
        mItem.add(item);
    }

}