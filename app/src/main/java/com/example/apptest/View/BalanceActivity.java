package com.example.apptest.View;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.apptest.Data.Transfer.Transfer;
import com.example.apptest.Data.Transfer.TransferInfo;
import com.example.apptest.Data.User.UserTokenInfo;
import com.example.apptest.R;
import com.example.apptest.service.Transfer.TransferService;
import com.example.apptest.service.User.UserTokenService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class BalanceActivity extends AppCompatActivity {

    // 토큰 정보 불러옴
    private SharedPreferences sharedPreferences;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

        textView = findViewById(R.id.TestTextView);

        Intent intent = getIntent();
        String finusernum = intent.getExtras().getString("finusernum");

        sharedPreferences = getSharedPreferences("TokenInfo", MODE_PRIVATE);
        String jwtToken = sharedPreferences.getString("jwtToken", "");


        if(jwtToken.equals(null)){

        } else {
            getUserInfoFromToken(jwtToken, finusernum);
        }

    }

    // 토큰으로 부터 accesstoken, seqno 알아옴
    public void getUserInfoFromToken(String jwtToken, String finusernum) {
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
                getTransList(Authorization, finusernum);

            }

            @Override
            public void onFailure(Call<UserTokenInfo> call, Throwable t) {

            }
        });
    }

    // 거래 목록
    public void getTransList(String Authorization, String funusernum){

        //transId 만들기
        Random rand = new Random();
        String rs = "";
        for(int i=0; i<9; i++){
            String ran = Integer.toString(rand.nextInt(10));
            rs += ran;
        }
        String transId = "T991671660U" + rs;

        String inquiry_type = "A";
        String inquiry_base = "D";

        String from_date = "20201101";

        Date time = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String to_date = format.format(time);

        String sort_order = "D";

        SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMddhhmmss");
        String tran_dtime = format2.format(time);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TransferService.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        TransferService service = retrofit.create(TransferService.class);
        service.fetchTransfer(Authorization, transId, funusernum, inquiry_type,inquiry_base, from_date, to_date, sort_order, tran_dtime)
                .enqueue(new Callback<TransferInfo>() {
            @Override
            public void onResponse(Call<TransferInfo> call, Response<TransferInfo> response) {
                List<Transfer> items = response.body().getResList()
                        .stream()
                        .collect(Collectors.toList());

                for(Transfer transfer:items){
                    textView.setText(transfer.getBranchName());
                }
            }

            @Override
            public void onFailure(Call<TransferInfo> call, Throwable t) {

            }
        });

    }

}