package com.example.apptest.View.Sign;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.apptest.Data.User.jwtToken;
import com.example.apptest.View.Main.MainActivity;
import com.example.apptest.R;
import com.example.apptest.service.User.UserService;

import java.util.List;

public class SignInActivity extends AppCompatActivity {
    Button SignUpBtn;
    Button LoginBtn;

    TextView textView;

    List<String> args;

    // 토큰 저장을 위해 사용
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        SignUpBtn = findViewById(R.id.SignUpBtn);
        LoginBtn = findViewById(R.id.LoginBtn);


        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = "해강1";
                String userPassword = "1234";

                textView = findViewById(R.id.IdTextView);

                // sql에서 user_id에 해당하는 userAccessToken, userSeqNo 받기
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(UserService.BASE_URL)
                        .addConverterFactory(MoshiConverterFactory.create())
                        .build();

                // Node.js 연동
                UserService service = retrofit.create(UserService.class);
                service.fetchUserInfo(userEmail, userPassword).enqueue(new Callback<jwtToken>() {
                    @Override
                    public void onResponse(Call<jwtToken> call, Response<jwtToken> response) {

                        String jwtToken = response.body().getJwtToken();

                        textView.setText(jwtToken);

                        sharedPreferences = getSharedPreferences("TokenInfo",MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        editor.putString("jwtToken", jwtToken);
                        editor.commit();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<jwtToken> call, Throwable t) {
                        textView.setText("실패");
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

