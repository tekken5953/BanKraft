package com.example.bankraft;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

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
    }

    public SideBarView(Context context) {
        this(context, null);
        init();
    }

    public SideBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.side_menu, this, true);
        findViewById(R.id.sidemenu_cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.sidemenu_cancel) {
            listener.btnCancel();
        }
    }
}
