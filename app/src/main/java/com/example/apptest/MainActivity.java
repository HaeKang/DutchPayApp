package com.example.apptest;

import androidx.appcompat.app.AppCompatActivity;
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

import com.example.apptest.BalanceActivity;
import com.example.apptest.Data.Account;
import com.example.apptest.Data.AccountInfo;
import com.example.apptest.Data.Balance;
import com.example.apptest.Data.UserTokenInfo;
import com.example.apptest.Data.jwtToken;
import com.example.apptest.R;
import com.example.apptest.service.AccountListService;
import com.example.apptest.service.BalanceService;
import com.example.apptest.service.UserTokenService;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    TextView AliasTextView;
    TextView FinNumTextView;
    TextView BalanceTextView;

    // 토큰 정보 불러옴
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AliasTextView = findViewById(R.id.AccountAliasText);
        FinNumTextView = findViewById(R.id.FinNumText);
        BalanceTextView = findViewById(R.id.BalanceText);

        sharedPreferences = getSharedPreferences("TokenInfo", MODE_PRIVATE);
        String jwtToken = sharedPreferences.getString("jwtToken", "");


        if(jwtToken.equals(null)){
            AliasTextView.setText("로그인이 필요함");
        } else {
            getUserInfoFromToken(jwtToken);
        }

        Button getTransListBtn = findViewById(R.id.GetTransListBtn);
        getTransListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BalanceActivity.class);
                intent.putExtra("finusernum", FinNumTextView.getText().toString());
                startActivity(intent);
            }
        });

    }


    // 토큰으로 부터 accesstoken, seqno 알아옴
    public void getUserInfoFromToken(String jwtToken){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UserTokenService.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        // 토큰으로 부터 accesstoken, userseqno 얻어옴
        UserTokenService service = retrofit.create(UserTokenService.class);

        service.fetchUserTokenInfo(jwtToken).enqueue(new Callback<UserTokenInfo>() {
            @Override
            public void onResponse(Call<UserTokenInfo> call, Response<UserTokenInfo> response) {
                String userSeqNo = response.body().getUserSeqNo();
                String userAccessToken = response.body().getUserAccessToken();

                String Authorization = "Bearer ";
                Authorization += userAccessToken;

                // 계좌 정보 불러오기
                getUserAccountInfo(Authorization, userSeqNo);

            }

            @Override
            public void onFailure(Call<UserTokenInfo> call, Throwable t) {

            }
        });

    }

    // 로그인한 유저의 계좌 정보
    public void getUserAccountInfo(String Authorization, String userSeqNo){
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

                    // 잔액 확인하기
                    getAccountBalance(Authorization, account.getFintechUseNum());

                }
            }

            @Override
            public void onFailure(Call<AccountInfo> call, Throwable t) {

            }
        });
    }


    // 잔액 확인
    public void getAccountBalance(String Authorization, String finusenum){

        //transId 만들기
        Random rand = new Random();
        String rs = "";
        for(int i=0; i<9; i++){
            String ran = Integer.toString(rand.nextInt(10));
            rs += ran;
        }
        String transId = "T991671660U" + rs;

        Date time = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
        String nowTime = format.format(time);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BalanceService.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        BalanceService service = retrofit.create(BalanceService.class);
        service.fetchBalance(Authorization, transId, finusenum, nowTime).enqueue(new Callback<Balance>() {
            @Override
            public void onResponse(Call<Balance> call, Response<Balance> response) {
                String balance = response.body().getBalanceAmt();
                BalanceTextView.setText(balance);
            }

            @Override
            public void onFailure(Call<Balance> call, Throwable t) {
                BalanceTextView.setText("실패");
            }
        });
    }

}