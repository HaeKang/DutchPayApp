package com.example.apptest;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.apptest.Data.AccountInfo;
import com.example.apptest.Data.Account;
import com.example.apptest.service.AccountListService;

import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button btn;

    private static final String TAG = "TAG_TEST";

    // api test
    private String token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAwNzY1NzkzIiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2MTM0NTc5OTMsImp0aSI6ImFkOWVjNzc2LTkxNjAtNGVjMi1iZTdiLTgyMGM0MjdjZTc2NyJ9.Y707X40lo-ZH50yntcQB1wsgKOMtmSgpxmPd3HOzBMM";
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(AccountListService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        btn = findViewById(R.id.button);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccountListService service = retrofit.create(AccountListService.class);

                service.fetchAccountInfo(token, 1100765793).enqueue(new Callback<AccountInfo>() {
                    @Override
                    public void onResponse(Call<AccountInfo> call, Response<AccountInfo> response) {
                        List<Account> items = response.body().getAccountInfo()
                                .stream()
                                .collect(Collectors.toList());

                        for(Account account : items){
                            Log.d(TAG, account.getFintechUseNum());
                        }
                    }

                    @Override
                    public void onFailure(Call<AccountInfo> call, Throwable t) {

                    }
                });

            }
        });

    }

}
