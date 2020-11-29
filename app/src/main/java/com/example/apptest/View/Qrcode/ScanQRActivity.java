package com.example.apptest.View.Qrcode;

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
import android.widget.Toast;

import com.example.apptest.Data.Deposit.DepositInfo;
import com.example.apptest.Data.Deposit.DepositReqList;
import com.example.apptest.Data.Token.oobToken;
import com.example.apptest.Data.User.UserTokenInfo;
import com.example.apptest.Data.Withdraw.Withdraw;
import com.example.apptest.R;
import com.example.apptest.View.WithdrawActivity;
import com.example.apptest.service.Deposit.DepositService;
import com.example.apptest.service.Deposit.OobTokenService;
import com.example.apptest.service.User.UserTokenService;
import com.example.apptest.service.Withdraw.WithdrawService;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ScanQRActivity extends AppCompatActivity {

    // 세로 방향으로 전환환
   private IntentIntegrator qrScan;
   public String tofinnum;
   public String money;

   TextView ScanQrFinText;
   TextView ScanQrMoney;
   Button QrDepositBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_q_r);

        Intent intent = getIntent();
        String myfin = intent.getStringExtra("finusernum");

        // QR 스캐너
        qrScan = new IntentIntegrator(this);
        qrScan.setOrientationLocked(false); // default가 세로모드인데 휴대폰 방향에 따라 가로, 세로로 자동 변경됩니다.
        qrScan.setPrompt("QR코드를 찍어주세용");
        qrScan.initiateScan();

        // layout
        ScanQrFinText = findViewById(R.id.ScanQrFinText);
        ScanQrMoney = findViewById(R.id.ScanQrMoney);
        QrDepositBtn = findViewById(R.id.QrDepositBtn);

        // 토큰 정보
        SharedPreferences sharedPreferences = getSharedPreferences("TokenInfo", MODE_PRIVATE);
        String jwtToken = sharedPreferences.getString("jwtToken", "");

        QrDepositBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 송금시작
                getUserInfoFromToken(jwtToken, myfin, tofinnum, money);
                Intent intent1 = new Intent(getApplicationContext(), WithdrawActivity.class);

            }
        });
    }

    // QR 스캔 결과 받음
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                // todo
                String results = result.getContents();
                String[] arr = results.split("/");

                tofinnum = arr[0];
                ScanQrFinText.setText("총무 계좌번호 : "+tofinnum);
                money = arr[1];
                ScanQrMoney.setText(money + "원을 보냅니다");

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    // 송금
    // 토큰으로 부터 accesstoken, seqno 알아옴
    public void getUserInfoFromToken(String jwtToken, String myfinnum, String tofinnum, String tranamt) {
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
                withdraw(jwtToken, Authorization, myfinnum, tofinnum, tranamt);
            }

            @Override
            public void onFailure(Call<UserTokenInfo> call, Throwable t) {

            }
        });
    }

    // 출금
    public void withdraw(String jwtToken, String Authorization, String myfinnum, String tofinnum, String tranamt){

        //transId 만들기
        Random rand = new Random();
        String rs = "";
        for(int i=0; i<9; i++){
            String ran = Integer.toString(rand.nextInt(10));
            rs += ran;
        }
        String bank_tran_id = "T991671660U" + rs;
        String cntr_account_type = "N";
        String cntr_account_num = "7836598807";
        String dps_print_content = "쇼핑몰환불";
        String fintech_use_num = myfinnum;
        String wd_print_content = "오픈뱅킹출금";
        String tran_amt = "10000";

        // 날짜
        Date time = new Date();
        SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMddhhmmss");
        String tran_dtime = format2.format(time);

        String req_client_name = "홍길동";
        String req_client_fintech_use_num = myfinnum;
        String req_client_num = "HONGGILDONG1234";
        String transfer_purpose = "ST";
        String recv_client_name = "유해강2";
        String recv_client_bank_code = "097";
        String recv_client_account_num = "1234123412";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WithdrawService.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        WithdrawService service = retrofit.create(WithdrawService.class);
        service.fetchWithdraw(Authorization, bank_tran_id, cntr_account_type, cntr_account_num, dps_print_content,
                fintech_use_num, wd_print_content, tran_amt, tran_dtime, req_client_name, req_client_fintech_use_num,
                req_client_num, transfer_purpose, recv_client_name, recv_client_bank_code, recv_client_account_num)
                .enqueue(new Callback<Withdraw>() {
                    @Override
                    public void onResponse(Call<Withdraw> call, Response<Withdraw> response) {
                        Log.d("test2", "withdraw 성공");
                        Log.d("test2", response.body().getRspCode());
                        Log.d("test2", response.body().getRspMessage());
                        OobToken(jwtToken, Authorization, myfinnum, tofinnum, tranamt);

                    }

                    @Override
                    public void onFailure(Call<Withdraw> call, Throwable t) {
                        Log.d("test2", "withdraw 실패");
                    }
                });
    }

    // 입금 전용 토큰
    public void OobToken(String jwtToken, String Authorization, String myfinnum, String tofinnum, String tranamt){
        String Content_type = "application/x-www-form-urlencoded";
        String client_id =  "NWK57GvwptnbK5U7UW8vx74tvM9nFh1SqGKSWver";
        String client_secret = "PAB8ov8OChnsFGFMatYh3YP0B8FHsr6Ndy0QGoeg";
        String scope = "oob";
        String grant_type = "client_credentials";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OobTokenService.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        OobTokenService service = retrofit.create(OobTokenService.class);
        service.fetchOobToken(Content_type, client_id, client_secret, scope, grant_type).enqueue(new Callback<oobToken>() {
            @Override
            public void onResponse(Call<oobToken> call, Response<oobToken> response) {
                Log.d("test2", "oob 토큰 성공");
                Log.d("test2", response.body().getAccessToken());
                String acctoken2 = response.body().getAccessToken();
                String Authorization = "Bearer " + acctoken2;
                Deposit(jwtToken, Authorization, myfinnum, tofinnum, tranamt);
            }

            @Override
            public void onFailure(Call<oobToken> call, Throwable t) {
                Log.d("test2", "oob 토큰 실패");
            }
        });
    }

    // 입금
    public void Deposit(String jwtToken, String Authorization, String myfinnum, String tofinnum, String tranamt){

        String cntr_account_type = "N";
        String cntr_account_num = "5612035165";
        String wd_pass_phrase = "NONE";
        String wd_print_content = "환불금액";
        String name_check_option = "on";

        // 날짜
        Date time = new Date();
        SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMddhhmmss");
        String tran_dtime = format2.format(time);

        String req_cnt = "1";

        //transId 만들기2
        Random rand = new Random();
        String rs = "";
        for(int i=0; i<9; i++){
            String ran = Integer.toString(rand.nextInt(10));
            rs += ran;
        }
        String bank_tran_id = "T991671660U" + rs;

        DepositReqList depositReqList = new DepositReqList();
        depositReqList.setBankTranId(bank_tran_id);
        depositReqList.setTranNo("1");
        depositReqList.setFintechUseNum(tofinnum);
        depositReqList.setPrintContent("오픈서비스캐시백");
        depositReqList.setTranAmt(tranamt);
        depositReqList.setReqClientName("김오픈");
        depositReqList.setReqClientNum("HONGGILDONG1234");
        depositReqList.setReqClientFintechUseNum(tofinnum);
        depositReqList.setTransferPurpose("ST");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DepositService.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        DepositService service = retrofit.create(DepositService.class);
        service.fetchDeposit(Authorization, cntr_account_type, cntr_account_num, wd_pass_phrase,
                wd_print_content, name_check_option, tran_dtime, req_cnt, depositReqList)
                .enqueue(new Callback<DepositInfo>() {
                    @Override
                    public void onResponse(Call<DepositInfo> call, Response<DepositInfo> response) {
                        Log.d("test2", "입금 성공");
                        Log.d("test2", response.body().getRspCode());
                    }

                    @Override
                    public void onFailure(Call<DepositInfo> call, Throwable t) {
                        Log.d("test2", "입금실패");
                    }
                });
    }

}