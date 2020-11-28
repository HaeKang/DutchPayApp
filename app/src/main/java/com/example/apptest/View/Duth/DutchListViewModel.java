package com.example.apptest.View.Duth;
import com.example.apptest.Data.Dutch.MyDutchListData;
import com.example.apptest.service.Dutch.MyDutchList;

import java.util.Collections;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class DutchListViewModel extends ViewModel {
    public String jwtToken;

    public MutableLiveData<List<MyDutchListData>> itemLiveData = new MutableLiveData<>();

    // jwtToken
    public void setJwtToken(String jwtToken){
        this.jwtToken = jwtToken;
    }

    public void getDutchList(){

        // 내 더치페이 목록
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyDutchList.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        MyDutchList DutchListService = retrofit.create(MyDutchList.class);
        DutchListService.fetchMyDutchList(jwtToken).enqueue(new Callback<List<MyDutchListData>>() {
            @Override
            public void onResponse(Call<List<MyDutchListData>> call, Response<List<MyDutchListData>> response) {
                List<MyDutchListData> items = response.body();
                itemLiveData.postValue(items);
            }

            @Override
            public void onFailure(Call<List<MyDutchListData>> call, Throwable t) {
                t.printStackTrace();
                itemLiveData.postValue(Collections.emptyList());
            }
        });

    }
}
