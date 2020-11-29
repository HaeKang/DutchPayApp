package com.example.apptest.View.Duth;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.apptest.Data.Dutch.MyDutchListData;
import com.example.apptest.Data.Dutch.MyQrData;
import com.example.apptest.R;
import com.example.apptest.View.Qrcode.QRcodeActivity;
import com.example.apptest.service.Dutch.MakeQrData;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class DutchListAdapter extends RecyclerView.Adapter<DutchListAdapter.DutchListViewHolder> {
    Context mContext;

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
        mContext = parent.getContext();
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

                int id = myDutchListData.getDutchid();
                String dutchid = Integer.toString(id);
                Log.d("test2", dutchid);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(MakeQrData.BASE_URL)
                        .addConverterFactory(MoshiConverterFactory.create())
                        .build();

                MakeQrData service = retrofit.create(MakeQrData.class);
                service.fetchQrData(dutchid).enqueue(new Callback<List<MyQrData>>() {
                    @Override
                    public void onResponse(Call<List<MyQrData>> call, Response<List<MyQrData>> response) {
                        List<MyQrData> item = response.body();
                        String finnum = item.get(0).getFinnum();
                        String money = item.get(0).getMoney();
                        String person = item.get(0).getPerson();

                        int money_i = Integer.parseInt(money);
                        int person_i = Integer.parseInt(person);
                        String pay_money = Integer.toString(money_i / person_i);

                        //QR코드 생성
                        Intent intent = new Intent(mContext, QRcodeActivity.class);
                        intent.putExtra("finusernum", finnum);
                        intent.putExtra("money", pay_money);
                        mContext.startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<List<MyQrData>> call, Throwable t) {
                        Log.d("test2", "실패");
                        t.printStackTrace();
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

}
