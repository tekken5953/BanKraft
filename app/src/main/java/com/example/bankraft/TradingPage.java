package com.example.bankraft;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bankraft.databinding.DoTradingBinding;

public class TradingPage extends AppCompatActivity {

    DoTradingBinding binding;

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

        buttonClick(tx1, tx2, tx3);
        buttonClick(tx2, tx1, tx3);
        buttonClick(tx3, tx1, tx2);

        binding.tradingCancel.setOnClickListener(view1 -> {
            Intent intent = new Intent(TradingPage.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void buttonClick(TextView btn_main, TextView stx1, TextView stx2) {
        btn_main.setOnClickListener(view1 -> {

            btn_main.setTextColor(Color.parseColor("#87ef83"));
            stx1.setTextColor(Color.parseColor("#FFFFFF"));
            stx2.setTextColor(Color.parseColor("#FFFFFF"));

        });
    }
}