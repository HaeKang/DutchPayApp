package com.example.apptest.View.Duth;

import android.util.Log;

import com.example.apptest.Data.Dutch.MyPayListData;
import com.example.apptest.service.Dutch.MyPayList;

import java.util.Collections;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class PayListViewModel extends ViewModel {

    public String jwtToken;

    public MutableLiveData<List<MyPayListData>> itemLiveData = new MutableLiveData<>();

    // jwtToken
    public void setJwtToken(String jwtToken){
        this.jwtToken = jwtToken;
    }

    public void getPayList(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyPayList.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        MyPayList service = retrofit.create(MyPayList.class);
        service.fetchMyPayList(jwtToken).enqueue(new Callback<List<MyPayListData>>() {
            @Override
            public void onResponse(Call<List<MyPayListData>> call, Response<List<MyPayListData>> response) {
                List<MyPayListData> items = response.body();
                itemLiveData.postValue(items);
                Log.d("test2", "지불해야하는 목록 성공");
            }

            @Override
            public void onFailure(Call<List<MyPayListData>> call, Throwable t) {
                t.printStackTrace();
                itemLiveData.postValue(Collections.emptyList());
                Log.d("test2", "지불해야하는 목록 실패");
            }
        });

    }


}
