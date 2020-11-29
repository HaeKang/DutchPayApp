package com.example.apptest.View;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import android.content.Intent;
import android.os.Bundle;

import com.example.apptest.R;
import com.example.apptest.service.Withdraw.WithdrawService;

public class WithdrawActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        Intent intent = getIntent();
        String myfinnum = intent.getStringExtra("myfinnum");
        String tofinnum = intent.getStringExtra("tofinnum");
        String tranamt = intent.getStringExtra("tranamt");


    }
}