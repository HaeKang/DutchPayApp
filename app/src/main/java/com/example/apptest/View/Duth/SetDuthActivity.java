package com.example.apptest.View.Duth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.apptest.Data.Account.Account;
import com.example.apptest.Data.Dutch.DutchInsertAns;
import com.example.apptest.R;
import com.example.apptest.service.Balance.BalanceService;
import com.example.apptest.service.Dutch.DutchInsert;

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
        String finusernum = intent.getStringExtra("finusernum");
        String dutchnane = intent.getStringExtra("name");

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

        // 토큰 정보
        SharedPreferences sharedPreferences = getSharedPreferences("TokenInfo", MODE_PRIVATE);
        String jwtToken = sharedPreferences.getString("jwtToken", "");

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
                // mysql에 넣음
                String dutchinfoname = NameText.getText().toString();
                String personnum = Integer.toString(items.size() + 1);
                ArrayList<String> personnames = new ArrayList<>();

                for(int i = 0; i<items.size(); i++){
                    String personname = items.get(i).getName();
                    personnames.add(personname);
                    Log.d("test2", personname);
                }

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(DutchInsert.BASE_URL)
                        .addConverterFactory(MoshiConverterFactory.create())
                        .build();

                DutchInsert service = retrofit.create(DutchInsert.class);
                service.fetchInsertDutch(jwtToken, dutchnane, finusernum, dutchinfoname, sum, personnum, personnames)
                        .enqueue(new Callback<DutchInsertAns>() {
                            @Override
                            public void onResponse(Call<DutchInsertAns> call, Response<DutchInsertAns> response) {
                                String ans = response.body().getState();
                                Log.d("test2", ans);
                                if(ans.equals("error")){

                                } else {
                                    Intent intent1 = new Intent(getApplicationContext(), DuthActivity.class);
                                    intent1.putExtra("finusernum", finusernum);
                                    startActivity(intent1);
                                }
                            }

                            @Override
                            public void onFailure(Call<DutchInsertAns> call, Throwable t) {

                            }
                        });
            }
        });
        
    }
}