package com.example.apptest.View.Duth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.apptest.Data.Deposit.DepositInfo;
import com.example.apptest.Data.Deposit.DepositReqList;
import com.example.apptest.Data.Dutch.MyPayListData;
import com.example.apptest.Data.Token.oobToken;
import com.example.apptest.Data.User.UserTokenInfo;
import com.example.apptest.Data.Withdraw.Withdraw;
import com.example.apptest.R;
import com.example.apptest.View.WithdrawActivity;
import com.example.apptest.service.Deposit.DepositService;
import com.example.apptest.service.Deposit.OobTokenService;
import com.example.apptest.service.Dutch.MyPayList;
import com.example.apptest.service.User.UserTokenService;
import com.example.apptest.service.Withdraw.WithdrawService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class PayListAdapter extends RecyclerView.Adapter<PayListAdapter.PayListViewHolder> {
    Context mContext;

    static class PayListViewHolder extends RecyclerView.ViewHolder{
        TextView dutchpayname_text;
        TextView ReceiverFinText;
        TextView BalanceText;
        Button DepositBtn;


        public PayListViewHolder(@NonNull View itemView) {
            super(itemView);

            dutchpayname_text = itemView.findViewById(R.id.dutchpayname_text);
            ReceiverFinText = itemView.findViewById(R.id.ReceiverFinText);
            BalanceText = itemView.findViewById(R.id.BalanceText);
            DepositBtn = itemView.findViewById(R.id.DepositBtn);
        }
    }

    private List<MyPayListData> mItems = new ArrayList<>();

    public void updateItems(List<MyPayListData> items){
        mItems = items;
        notifyDataSetChanged(); // ui 갱신
    }

    @NonNull
    @Override
    public PayListAdapter.PayListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pay_list, parent, false);
        mContext = parent.getContext();
        return new PayListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PayListAdapter.PayListViewHolder holder, int position) {
        MyPayListData myPayListData = mItems.get(position);
        holder.dutchpayname_text.setText(myPayListData.getName());
        int pay_money = myPayListData.getMoney() / myPayListData.getPerson();
        holder.BalanceText.setText(Integer.toString(pay_money) + "원 입금해야함");
        holder.ReceiverFinText.setText("총무 : " +myPayListData.getFinnum());

        String myfinnum = myPayListData.getMyfinnum();
        String tofinnum = myPayListData.getFinnum();

        // 출금 -> 입금 시작
        holder.DepositBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 토큰 정보
                SharedPreferences sharedPreferences;
                sharedPreferences =  mContext.getSharedPreferences("TokenInfo", mContext.MODE_PRIVATE);
                String jwtToken = sharedPreferences.getString("jwtToken", "");
                Log.d("test2", jwtToken);

                getUserInfoFromToken(jwtToken, myfinnum, tofinnum, Integer.toString(pay_money));

            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

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

                Intent intent = new Intent(mContext, WithdrawActivity.class);
                intent.putExtra("myfinnum", myfinnum);
                intent.putExtra("tofinnum", tofinnum);
                intent.putExtra("tranamt", tranamt);
                mContext.startActivity(intent);
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
        String tran_amt = tranamt;

        // 날짜
        Date time = new Date();
        SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMddhhmmss");
        String tran_dtime = format2.format(time);

        String req_client_name = "홍길동";
        String req_client_fintech_use_num = myfinnum;
        String req_client_num = "HONGGILDONG1234";
        String transfer_purpose = "ST";
        String recv_client_name = "진상언";
        String recv_client_bank_code = "097";
        String recv_client_account_num = "7836598807";

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
                        deletePayList(jwtToken);
                    }

                    @Override
                    public void onFailure(Call<DepositInfo> call, Throwable t) {
                        Log.d("test2", "입금실패");
                    }
                });
    }

    public void deletePayList(String jwtToken){

    }



}
