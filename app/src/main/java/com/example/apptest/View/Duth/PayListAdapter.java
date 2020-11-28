package com.example.apptest.View.Duth;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.apptest.Data.Dutch.MyPayListData;
import com.example.apptest.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PayListAdapter extends RecyclerView.Adapter<PayListAdapter.PayListViewHolder> {
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
        return new PayListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PayListAdapter.PayListViewHolder holder, int position) {
        MyPayListData myPayListData = mItems.get(position);
        holder.dutchpayname_text.setText(myPayListData.getName());
        int pay_money = myPayListData.getMoney() / myPayListData.getPerson();
        holder.BalanceText.setText(Integer.toString(pay_money) + "원 입금해야함");
        holder.ReceiverFinText.setText("총무 : " +myPayListData.getFinnum());

        // 입금 과정 넣기
        holder.DepositBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
