package com.example.bankraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.bankraft.databinding.ActivitySearchBinding;
import com.example.bankraft.databinding.ActivitySplashBinding;
import com.google.android.material.snackbar.Snackbar;

public class SearchActivity extends AppCompatActivity {

    ActivitySearchBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.searchBack.setOnClickListener(view1 -> {
          Intent intent = new Intent(SearchActivity.this, HomeActivity.class);
          startActivity(intent);
          finish();
        });

        binding.searchDo.setOnClickListener(view1 -> Toast.makeText(SearchActivity.this, "Do Searching", Toast.LENGTH_SHORT).show());
    }
}