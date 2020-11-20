package com.example.apptest.Sign;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.apptest.Data.Account;
import com.example.apptest.Data.AccountInfo;
import com.example.apptest.R;
import com.example.apptest.service.AccountListService;

import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    // 계좌 리스트 불러옴
    
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(AccountListService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build();

    String Authorization = "Bearer ";
    int userSeqNo;

    TextView AliasTextView;
    TextView FinNumTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String userid = intent.getExtras().getString("userid");
        String userAccessToken = intent.getExtras().getString("userAccessToken");
        String userSeqNo_str = intent.getExtras().getString("userSeqNo");

        Authorization += userAccessToken;
        userSeqNo = Integer.parseInt(userSeqNo_str);

        AliasTextView = findViewById(R.id.AccountAliasText);
        FinNumTextView = findViewById(R.id.FinNumText);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AccountListService.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        AccountListService service = retrofit.create(AccountListService.class);

        service.fetchAccountInfo(Authorization, userSeqNo).enqueue(new Callback<AccountInfo>() {
            @Override
            public void onResponse(Call<AccountInfo> call, Response<AccountInfo> response) {
                List<Account> items = response.body().getAccountInfo()
                        .stream()
                        .collect(Collectors.toList());

                for(Account account : items){
                    AliasTextView.setText(account.getAccountAlias());
                    FinNumTextView.setText(account.getFintechUseNum());
                }
            }

            @Override
            public void onFailure(Call<AccountInfo> call, Throwable t) {

            }
        });

    }
}