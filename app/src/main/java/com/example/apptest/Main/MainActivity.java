package com.example.apptest.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.apptest.BalanceActivity;
import com.example.apptest.Data.Account.Account;
import com.example.apptest.Qrcode.QRcodeActivity;
import com.example.apptest.Qrcode.ScanQRActivity;
import com.example.apptest.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // 토큰 정보
        SharedPreferences sharedPreferences = getSharedPreferences("TokenInfo", MODE_PRIVATE);
        String jwtToken = sharedPreferences.getString("jwtToken", "");


        if(jwtToken.equals(null)){

        } else {
            viewModel.setJwtToken(jwtToken);
        }

        // QR 스캐너
        Button getQrScanBtn = findViewById(R.id.GetQrScan);
        getQrScanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ScanQRActivity.class);
                startActivity(intent);
            }
        });


        // recycler view
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        final AccountAdapter adapter = new AccountAdapter();
        recyclerView.setAdapter(adapter);

        viewModel.getUserInfoFromToken();

        viewModel.itemLiveData.observe(this, accounts -> {
            adapter.updateItems(accounts);
        });


        // 로딩 바
        viewModel.loadingLiveData.observe(this, isLoading -> {
            if (isLoading) {
                findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
            } else {
                findViewById(R.id.progressBar).setVisibility(View.GONE);
            }
        });

    }

}


// 리사이클러뷰 adapter
class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder>{

    static class AccountViewHolder extends RecyclerView.ViewHolder{
        TextView AccountAliasText;
        TextView AccountFinText;
        TextView AccountBalanceText;
        Button AccountTransBtn;
        Button AccountQrBtn;

        public AccountViewHolder(@NonNull View itemview){
            super(itemview);

            AccountAliasText = itemview.findViewById(R.id.AccountAliasText);
            AccountFinText = itemview.findViewById(R.id.AccountFinText);
            AccountBalanceText = itemview.findViewById(R.id.AccountBalanceText);
            AccountTransBtn = itemview.findViewById(R.id.AccountTransBtn);
            AccountQrBtn = itemview.findViewById(R.id.AccountQrBtn);
        }
    }

    private List<Account> mItems = new ArrayList<>();

    public void updateItems(List<Account> items){
        mItems = items;
        notifyDataSetChanged(); // ui 갱신
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account, parent, false);
        return new AccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int i) {
        Account account = mItems.get(i);

        holder.AccountAliasText.setText(account.getAccountAlias());
        holder.AccountBalanceText.setText(account.getBalance());
        holder.AccountFinText.setText(account.getFintechUseNum());

        // 거래내역 버튼 클릭
        holder.AccountTransBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(view.getContext(), BalanceActivity.class);
                intent.putExtra("finusernum", account.getFintechUseNum());
                context.startActivity(intent);
            }
        });

        // QR생성 버튼 클릭
        holder.AccountQrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(view.getContext(), QRcodeActivity.class);
                intent.putExtra("finusernum", account.getFintechUseNum());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

}