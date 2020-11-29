package com.example.apptest.View.Duth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import com.example.apptest.View.Qrcode.ScanQRActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


import com.example.apptest.R;


public class DuthActivity extends AppCompatActivity {

    // fab 애니메이션 변수들
    private Context mContext;
    private FloatingActionButton fab, fab_sub1, fab_sub2;
    private Animation fab_open, fab_close;
    private boolean isFabOpen = false;
    private String finusernum;


    // recyclerview
    RecyclerView dutchlist_recyclerview;
    RecyclerView paylist_recyclerview;

    // viewmodel
    private DutchListViewModel viewModelDutch;
    private PayListViewModel viewModelPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duth);
        Intent intent = getIntent();
        finusernum = intent.getStringExtra("finusernum");

        // 토큰 정보
        SharedPreferences sharedPreferences = getSharedPreferences("TokenInfo", MODE_PRIVATE);
        String jwtToken = sharedPreferences.getString("jwtToken", "");

        // fab 애니메이션
        mContext = getApplicationContext();
        fab_open = AnimationUtils.loadAnimation(mContext, R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(mContext, R.anim.fab_close);

        fab = findViewById(R.id.fab);
        fab_sub1 = findViewById(R.id.fab_sub1); // qr리더기
        fab_sub2 = findViewById(R.id.fab_sub2); // 더치페이 만들기

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleFab();
            }
        });

        fab_sub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleFab();
                Intent intent = new Intent(mContext, ScanQRActivity.class);
                intent.putExtra("finusernum", finusernum);
                startActivity(intent);
            }
        });

        fab_sub2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleFab();
                Intent intent2 = new Intent(getApplicationContext(), AddDuthActivity.class);
                intent2.putExtra("finusernum", finusernum);
                startActivity(intent2);
            }
        });

        // recyclerview
        dutchlist_recyclerview = findViewById(R.id.dutchlist_recyclerview);
        paylist_recyclerview = findViewById(R.id.paylist_recyclerview);

        // 내가 총무인 목록
        dutchlist_recyclerview.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        final DutchListAdapter Dutchadapter = new DutchListAdapter();
        dutchlist_recyclerview.setAdapter(Dutchadapter);

        viewModelDutch = new ViewModelProvider(this).get(DutchListViewModel.class);
        viewModelDutch.setJwtToken(jwtToken);
        viewModelDutch.getDutchList();
        viewModelDutch.itemLiveData.observe(this, DutchLists -> {
            Dutchadapter.updateItems(DutchLists);
        });

        // 지불해야하는 목록
        paylist_recyclerview.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        final PayListAdapter PayAdapter = new PayListAdapter();
        paylist_recyclerview.setAdapter(PayAdapter);

        viewModelPay = new ViewModelProvider(this).get(PayListViewModel.class);
        viewModelPay.setJwtToken(jwtToken);
        viewModelPay.setFinnum(finusernum);
        viewModelPay.getPayList();
        viewModelPay.itemLiveData.observe(this, PayLists -> {
            PayAdapter.updateItems(PayLists);
        });


    }

    private void toggleFab(){
        if(isFabOpen){
            fab_sub1.startAnimation(fab_close);
            fab_sub2.startAnimation(fab_close);
            fab_sub1.setClickable(false);
            fab_sub2.setClickable(false);
            isFabOpen = false;
        } else {
            fab_sub1.startAnimation(fab_open);
            fab_sub2.startAnimation(fab_open);
            fab_sub1.setClickable(true);
            fab_sub2.setClickable(true);
            isFabOpen = true;
        }
    }

}