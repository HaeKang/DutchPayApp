package com.example.apptest.View.Main;

import android.util.Log;
import android.widget.Toast;

import com.example.apptest.Data.Account.Account;
import com.example.apptest.Data.Account.AccountInfo;
import com.example.apptest.Data.Balance.Balance;
import com.example.apptest.Data.User.UserTokenInfo;
import com.example.apptest.service.Account.AccountListService;
import com.example.apptest.service.Balance.BalanceService;
import com.example.apptest.service.User.UserTokenService;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;


public class MainViewModel extends ViewModel {

    public String jwtToken;

    // account 아이템
    public MutableLiveData<List<Account>> itemLiveData = new MutableLiveData<>();

    // 로딩바
    public MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();

    // jwtToken
    public void setJwtToken(String jwtToken){
        this.jwtToken = jwtToken;
    }


    // 토큰으로 부터 accesstoken, seqno 알아옴
    public void getUserInfoFromToken(){
        // 로딩 시작
        loadingLiveData.setValue(true);

        // api 시작
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
                // 로딩 끝
                loadingLiveData.postValue(false);
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

                int for_length = 0;

                for(Account account : items){
                    // for문 다 돌고 recyclerview에 넣기 위해 사용
                    for_length++;
                    int finalFor_length = for_length;

                    // 잔액 확인하기
                    String finusenum = account.getFintechUseNum();

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
                            account.setBalance(balance);

                            // 마지막 for문일때
                            if(finalFor_length == items.size()){
                                itemLiveData.postValue(items);
                                loadingLiveData.postValue(false);
                            }
                        }

                        @Override
                        public void onFailure(Call<Balance> call, Throwable t) {

                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<AccountInfo> call, Throwable t) {
                itemLiveData.postValue(Collections.emptyList());
                // 로딩 끝
                loadingLiveData.postValue(false);
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

            }

            @Override
            public void onFailure(Call<Balance> call, Throwable t) {

            }
        });

    }

}
