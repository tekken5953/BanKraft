package com.example.bankraft.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bankraft.HomeActivity;
import com.example.bankraft.R;
import com.example.bankraft.SharedPreferenceManager;
import com.example.bankraft.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference fb_login = database.getReference("User");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        EditText login_id = binding.loginIdEdt;
        EditText login_pwd = binding.loginPwdEdt;
        Button loginBtn = binding.loginBtn;
        TextView signUpTx = binding.signUpTx;

        login_pwd.setTextColor(Color.WHITE);

        //회원가입 클릭 시 이벤트
        signUpTx.setOnClickListener(view1 -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View v = LayoutInflater.from(LoginActivity.this).inflate(R.layout.sign_up, null, false);
            builder.setView(v);
            final AlertDialog alertDialog = builder.create();
            final Button sign_up_ok = v.findViewById(R.id.sign_up_ok);
            final Button sign_up_cancel = v.findViewById(R.id.sign_up_cancel);
            final EditText sign_up_id = v.findViewById(R.id.sign_up_id);
            final EditText sign_up_pwd = v.findViewById(R.id.sign_up_pwd);
            final EditText sign_up_name = v.findViewById(R.id.sign_up_name);
            final EditText sign_up_repwd = v.findViewById(R.id.sign_up_repwd);
            final TextView warning_msg = v.findViewById(R.id.warning_msg);

            //경고 메시지 밑줄
            SpannableString content = new SpannableString(warning_msg.getText().toString());
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            warning_msg.setText(content);

            //회원가입 확인 버튼 클릭
            sign_up_ok.setOnClickListener(view2 -> {
                if (sign_up_id.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "아이디를 입력 해 주세요.", Toast.LENGTH_SHORT).show();
                    keyboardUp(sign_up_id);
                } else if (sign_up_name.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "이름을 입력 해 주세요.", Toast.LENGTH_SHORT).show();
                    keyboardUp(sign_up_name);
                } else if (sign_up_pwd.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "비밀번호를 입력 해 주세요.", Toast.LENGTH_SHORT).show();
                    keyboardUp(sign_up_pwd);
                } else if (sign_up_repwd.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "비밀번호 확인을 입력 해 주세요.", Toast.LENGTH_SHORT).show();
                    keyboardUp(sign_up_repwd);
                } else if (sign_up_id.length() < 8 || sign_up_id.length() > 20) {
                    Toast.makeText(LoginActivity.this, "아이디의 길이를 확인해주세요.", Toast.LENGTH_SHORT).show();
                    sign_up_id.setText("");
                    keyboardUp(sign_up_id);
                } else if (!sign_up_pwd.getText().toString().equals(sign_up_repwd.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    keyboardUp(sign_up_pwd);
                } else {
                    Toast.makeText(LoginActivity.this, "가입이 처리되었습니다.\n 서비스를 이용하실 수 있습니다.", Toast.LENGTH_SHORT).show();
                    fb_login.child(sign_up_id.getText().toString()).child("id").setValue(sign_up_id.getText().toString());
                    fb_login.child(sign_up_id.getText().toString()).child("name").setValue(sign_up_name.getText().toString());
                    fb_login.child(sign_up_id.getText().toString()).child("pwd").setValue(sign_up_pwd.getText().toString());
                    alertDialog.dismiss();
                }
            });

            //회원가입 취소 버튼 클릭
            sign_up_cancel.setOnClickListener(view3 -> {
                alertDialog.dismiss();
            });

            alertDialog.show();
        });

        //로그인 버튼 클릭
        loginBtn.setOnClickListener(view1 -> {

            if (login_id.getText().toString().equals("")) {
                Toast.makeText(LoginActivity.this, "아이디를 입력 해 주세요.", Toast.LENGTH_SHORT).show();
            } else if (login_pwd.getText().toString().equals("")) {
                Toast.makeText(LoginActivity.this, "비밀번호를 입력 해 주세요.", Toast.LENGTH_SHORT).show();
            } else {
                fb_login.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        try {
                            dataSnapshot.child(login_id.getText().toString()).getValue().toString();
                            if (Objects.equals(dataSnapshot.child(login_id.getText().toString()).child("id").getValue().toString(), login_id.getText().toString())) {
                                if (Objects.equals(dataSnapshot.child(login_id.getText().toString()).child("pwd").getValue().toString(), login_pwd.getText().toString())) {
                                    //현재 접속 시간 측정
                                    final Date currentTime = Calendar.getInstance().getTime();
                                    final SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy" + "년 " + "MM" + "월 "
                                            + "dd" + "일 " + "hh" + "시 " + "mm" + "분 " + "ss" + "초" , Locale.getDefault());
                                    final String date = dayFormat.format(currentTime);

                                    //로그인 정보 저장
                                    SharedPreferenceManager.setString(LoginActivity.this,"enter_clock", date); //접속 시간
                                    SharedPreferenceManager.setString(LoginActivity.this, "user_name", dataSnapshot.child(login_id.getText().toString()).child("name").getValue().toString()); //유저 이름
                                    Toast.makeText(LoginActivity.this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    overridePendingTransition(0, 0);
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "비밀번호가 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
                                }
                            }

                        } catch (Exception e) {
                            Toast.makeText(LoginActivity.this, "등록되지 않은 사용자입니다.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(LoginActivity.this, "로그인에 실패하였습니다.1", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void keyboardUp(EditText editText) {
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        //focus 후 키보드 올리기
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
}