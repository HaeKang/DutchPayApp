package com.example.apptest.Sign;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptest.Data.UserInfo;
import com.example.apptest.R;
import com.example.apptest.service.AccountListService;
import com.example.apptest.service.UserService;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class SignInActivity extends AppCompatActivity {
    Button SignUpBtn;
    Button LoginBtn;

    TextView textView;

    List<String> args;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        SignUpBtn = findViewById(R.id.SignUpBtn);
        LoginBtn = findViewById(R.id.LoginBtn);


        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = "해강1";

                textView = findViewById(R.id.IdTextView);

                // sql에서 user_id에 해당하는 userAccessToken, userSeqNo 받기
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(UserService.BASE_URL)
                        .addConverterFactory(MoshiConverterFactory.create())
                        .build();

                // Node.js 연동
                UserService service = retrofit.create(UserService.class);
                service.fetchUserInfo(userId).enqueue(new Callback<UserInfo>() {
                    @Override
                    public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                        String userAccessToken = response.body().getUserAccessToken();
                        String userSeqNo = response.body().getUserSeqNo();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("userid", "해강1");
                        intent.putExtra("userAccessToken", userAccessToken);
                        intent.putExtra("userSeqNo", userSeqNo);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<UserInfo> call, Throwable t) {

                    }
                });

            }
        });




        SignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivityForResult(intent, 1001);
            }
        });
    }

}

