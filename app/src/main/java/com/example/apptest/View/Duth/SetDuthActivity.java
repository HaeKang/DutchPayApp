package com.example.apptest.View.Duth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.apptest.Data.Account.Account;
import com.example.apptest.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.jar.Attributes;

public class SetDuthActivity extends AppCompatActivity {

    TextView SumText;
    TextView NameText;
    TextView DayText;

    Button SetAddPersonBtn;
    Button SetFinDuthBtn;

    // person 아이템
    public MutableLiveData<List<SetDuthPerson>> itemLiveData = new MutableLiveData<>();
    // 아이템 삭제 itemLiveData.postValue(Collections.emptyList());
    public List<SetDuthPerson> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_duth);

        Intent intent = getIntent();
        String sum = intent.getStringExtra("sum");  // 총액

        SumText = findViewById(R.id.SetSumTextView);
        NameText = findViewById(R.id.SetDuthNameTextView);
        DayText = findViewById(R.id.SetDayTextView);
        SetAddPersonBtn = findViewById(R.id.SetAddPersonBtn);
        SetFinDuthBtn = findViewById(R.id.SetFinDuthBtn);

        SumText.setText("총액 : " + sum + "원");

        // 오늘 날짜
        Date time = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String nowTime = format.format(time);
        DayText.setText(nowTime);


        // recycler view
        RecyclerView duth_recycler_view = findViewById(R.id.duth_recycler_view);
        duth_recycler_view.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        final SetDuthAdapter setDuthAdapter = new SetDuthAdapter();
        duth_recycler_view.setAdapter(setDuthAdapter);


        // 사람 추가
        SetAddPersonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetDuthPerson person = new SetDuthPerson("사람", false, false);
                items.add(person);
                itemLiveData.postValue(items);
            }
        });

        itemLiveData.observe(this, items -> {
            setDuthAdapter.updateItems(items);
        });


        // 저장
        SetFinDuthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        
    }
}