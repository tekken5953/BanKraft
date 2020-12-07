package com.example.bankraft;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class SideBarView extends ConstraintLayout implements View.OnClickListener {

    // 메뉴버튼 클릭 이벤트 리스너
    public EventListener listener;

    public void setEventListener(EventListener l) {
        listener = l;
    }

    // 메뉴버튼 클릭 이벤트 리스너 인터페이스
    public interface EventListener {
        // 닫기 버튼 클릭 이벤트
        void btnCancel();
        void logOut();
    }

    public SideBarView(Context context) {
        this(context, null);
        init();
    }

    public SideBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @SuppressLint("SetTextI18n")
    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.side_menu, this, true);
        findViewById(R.id.sidemenu_cancel).setOnClickListener(this);
        findViewById(R.id.slide_logout).setOnClickListener(this);
        TextView user_name = findViewById(R.id.side_user_name);
        TextView user_clock = findViewById(R.id.side_clock);
        user_name.setText(SharedPreferenceManager.getString(getContext(),"user_name") + " 님");
        user_clock.setText(SharedPreferenceManager.getString(getContext(), "enter_clock"));
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.sidemenu_cancel :
                listener.btnCancel();
                break;

            case R.id.slide_logout :
                listener.logOut();
                break;
        }

    }
}
