package com.example.bankraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        AutoCompleteTextView textView = binding.autoCompleteTextView;
        textView.setAdapter(adapter);

        binding.searchBack.setOnClickListener(view1 -> {
          Intent intent = new Intent(SearchActivity.this, HomeActivity.class);
          startActivity(intent);
          finish();
        });

        binding.searchDo.setOnClickListener(view1 -> Toast.makeText(SearchActivity.this, "Do Searching", Toast.LENGTH_SHORT).show());
    }

    private static final String[] COUNTRIES = new String[] {
            "test1", "test2", "test3", "test4", "test5", "test6", "test7"
    };
}