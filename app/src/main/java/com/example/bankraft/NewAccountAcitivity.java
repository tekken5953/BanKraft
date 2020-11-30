package com.example.bankraft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.bankraft.databinding.NewAccountBinding;

public class NewAccountAcitivity extends AppCompatActivity {

    NewAccountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = NewAccountBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

    }
}