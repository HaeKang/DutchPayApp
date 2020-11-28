package com.example.apptest.View.Duth;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.apptest.Data.Dutch.MyDutchListData;
import com.example.apptest.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DutchListAdapter extends RecyclerView.Adapter<DutchListAdapter.DutchListViewHolder> {

    static class DutchListViewHolder extends RecyclerView.ViewHolder{
        TextView dutchpayname_text;
        TextView dutchday_text;
        Button AddDutchBtn;

        public DutchListViewHolder(@NonNull View itemview){
            super(itemview);

            dutchpayname_text = itemview.findViewById(R.id.dutchpayname_text);
            dutchday_text = itemview.findViewById(R.id.dutchday_text);
            AddDutchBtn = itemview.findViewById(R.id.AddDutchBtn);
        }
    }

    private List<MyDutchListData> mItems = new ArrayList<>();

    public void updateItems(List<MyDutchListData> items){
        mItems = items;
        notifyDataSetChanged(); // ui 갱신
    }

    @NonNull
    @Override
    public DutchListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dutch_list, parent, false);
        return new DutchListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DutchListViewHolder holder, int i) {
        MyDutchListData myDutchListData = mItems.get(i);
        holder.dutchpayname_text.setText(myDutchListData.getName());
        Log.d("test2", myDutchListData.getName());
        holder.dutchday_text.setText(myDutchListData.getFinnum());

        holder.AddDutchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 내역 추가
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

}
