package com.example.apptest.View.Duth;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.example.apptest.R;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SetDuthAdapter extends RecyclerView.Adapter<SetDuthAdapter.SetDuthViewHolder> {

    static class SetDuthViewHolder extends RecyclerView.ViewHolder{
        TextView item_set_duth_name;
        TextView item_set_duth_edit;
        CheckBox SetDuthJoinCheck;
        CheckBox SetDuthPayCheck;

        public SetDuthViewHolder(@NonNull View itemview){
            super(itemview);

            item_set_duth_name = itemview.findViewById(R.id.item_set_duth_name);
            item_set_duth_edit = itemview.findViewById(R.id.item_set_duth_edit);
            SetDuthJoinCheck = itemview.findViewById(R.id.SetDuthJoinCheck);
            SetDuthPayCheck = itemview.findViewById(R.id.SetDuthPayCheck);
        }
    }

    private List<SetDuthPerson> mItems = new ArrayList<>();

    public void updateItems(List<SetDuthPerson> items){
        mItems = items;
        notifyDataSetChanged(); // ui 갱신
    }

    @NonNull
    @Override
    public SetDuthViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_duth_pepole, parent, false);
        return new SetDuthViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SetDuthViewHolder holder, int i) {
        SetDuthPerson person = mItems.get(i);

        if(person.getName().equals(null)){
            holder.item_set_duth_name.setText("왜?");
        } else {
            holder.item_set_duth_name.setText(person.getName());
        }

        // 체크박스 설정하기
        holder.SetDuthJoinCheck.setOnClickListener(new CheckBox.OnClickListener(){
            @Override
            public void onClick(View view) {
                person.setJoin(true);
            }
        });

        holder.SetDuthPayCheck.setOnClickListener(new CheckBox.OnClickListener(){
            @Override
            public void onClick(View view) {
                person.setPay(true);
            }
        });


        // 수정 텍스트 클릭
        holder.item_set_duth_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 수정 팝업 열기
                person.setName("사람2");
                updateItems(mItems);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


}
