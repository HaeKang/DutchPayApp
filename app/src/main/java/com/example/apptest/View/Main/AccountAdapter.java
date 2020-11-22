package com.example.apptest.View.Main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptest.Data.Account.Account;
import com.example.apptest.R;
import com.example.apptest.View.BalanceActivity;
import com.example.apptest.View.Duth.DuthActivity;
import com.example.apptest.View.Qrcode.QRcodeActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        holder.AccountFinText.setText(account.getFintechUseNum());
        holder.AccountBalanceText.setText("잔액 : " + account.getBalance());

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
                /*
                Context context = view.getContext();
                Intent intent = new Intent(view.getContext(), QRcodeActivity.class);
                intent.putExtra("finusernum", account.getFintechUseNum());
                context.startActivity(intent);
                 */

                // 더치페이 생성으로 이동
                Context context = view.getContext();
                Intent intent = new Intent(view.getContext(), DuthActivity.class);
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