package com.example.bankraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.bankraft.databinding.SearchActivityBinding;

public class SearchActivity extends AppCompatActivity {

    SearchActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SearchActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.dropdowncustom, SEARCH_TITLE);
        AutoCompleteTextView textView = binding.autoCompleteTextView;
        //TODO 아이템 별 액티비티 이동 구현
        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        textView.setAdapter(adapter);

        binding.searchBack.setOnClickListener(view1 -> {
            Intent intent = new Intent(SearchActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });

        //TODO 검색어 별 액티비티로 이동
        binding.searchDo.setOnClickListener(view1 -> Toast.makeText(SearchActivity.this, "Do Searching", Toast.LENGTH_SHORT).show());
    }

    private static final String[] SEARCH_TITLE = new String[]{
            "test   >   test1   >   test1", "test   >   test2   >   test2", "test   >   test3   >   test3", "test   >   test4   >   test4",
            "test   >   test5   >   test5", "test   >   test6   >   test6", "test   >   test7   >   test7"
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SearchActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}