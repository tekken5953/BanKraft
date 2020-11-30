package com.example.bankraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.bankraft.databinding.TradingListBinding;

public class TradingListActivity extends AppCompatActivity {

    TradingListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TradingListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.back.setOnClickListener(view1 -> {
            Intent intent = new Intent(TradingListActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });
    }
}