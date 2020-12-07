package com.example.bankraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bankraft.databinding.NotificationBinding;

public class NotificationActivity extends AppCompatActivity {

    NotificationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = NotificationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Button btn1 = binding.notifyBtn1;
        Button btn2 = binding.notifyBtn2;
        Button btn3 = binding.notifyBtn3;
        Button btn4 = binding.notifyBtn4;

        binding.notifyBack.setOnClickListener(view1 -> {
            Intent intent = new Intent(NotificationActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });

        buttonClick(btn1, btn2, btn3, btn4);
        buttonClick(btn2, btn1, btn3, btn4);
        buttonClick(btn3, btn1, btn2, btn4);
        buttonClick(btn4, btn1, btn2, btn3);

    }

    private void buttonClick(Button btn_main, Button sbtn1, Button sbtn2, Button sbtn3) {
        btn_main.setOnClickListener(view1 -> {
            btn_main.setTextColor(Color.parseColor("#87ef83"));
            sbtn1.setTextColor(Color.parseColor("#FFFFFF"));
            sbtn2.setTextColor(Color.parseColor("#FFFFFF"));
            sbtn3.setTextColor(Color.parseColor("#FFFFFF"));
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(NotificationActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}