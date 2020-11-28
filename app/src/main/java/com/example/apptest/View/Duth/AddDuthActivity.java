package com.example.apptest.View.Duth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.apptest.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddDuthActivity extends AppCompatActivity {

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btn00;
    Button btn0;
    Button btnSetZero;
    Button btnFin;
    ImageButton btnDelete;

    TextView SumTextView;

    String history = "";

    String dutchname = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_duth);

        btn1 = findViewById(R.id.Btn1);
        btn2 = findViewById(R.id.Btn2);
        btn3 = findViewById(R.id.Btn3);
        btn4 = findViewById(R.id.Btn4);
        btn5 = findViewById(R.id.Btn5);
        btn6 = findViewById(R.id.Btn6);
        btn7 = findViewById(R.id.Btn7);
        btn8 = findViewById(R.id.Btn8);
        btn9 = findViewById(R.id.Btn9);
        btn00 = findViewById(R.id.Btn00);
        btn0 = findViewById(R.id.Btn0);
        btnSetZero = findViewById(R.id.BtnSetZero);
        btnFin = findViewById(R.id.BtnFin);
        btnDelete = findViewById(R.id.BtnDelete);

        SumTextView = findViewById(R.id.SumTextView);

        Intent intent = getIntent();
        String finusernum = intent.getStringExtra("finusernum");

        // 오늘 날짜
        Date time = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String nowTime = format.format(time);
        dutchname = nowTime + " 더치페이";

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history = SumTextView.getText().toString();
                if(history.equals("0") && history.length() == 1){
                    SumTextView.setText("1");
                } else {
                    SumTextView.setText(history + "1");
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history = SumTextView.getText().toString();
                if(history.equals("0") && history.length() == 1){
                    SumTextView.setText("2");
                } else {
                    SumTextView.setText(history + "2");
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history = SumTextView.getText().toString();
                if(history.equals("0") && history.length() == 1){
                    SumTextView.setText("3");
                } else {
                    SumTextView.setText(history + "3");
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history = SumTextView.getText().toString();
                if(history.equals("0") && history.length() == 1){
                    SumTextView.setText("4");
                } else {
                    SumTextView.setText(history + "4");
                }
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history = SumTextView.getText().toString();
                if(history.equals("0") && history.length() == 1){
                    SumTextView.setText("5");
                } else {
                    SumTextView.setText(history + "5");
                }
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history = SumTextView.getText().toString();
                if(history.equals("0") && history.length() == 1){
                    SumTextView.setText("6");
                } else {
                    SumTextView.setText(history + "6");
                }
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history = SumTextView.getText().toString();
                if(history.equals("0") && history.length() == 1){
                    SumTextView.setText("7");
                } else {
                    SumTextView.setText(history + "7");
                }
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history = SumTextView.getText().toString();
                if(history.equals("0") && history.length() == 1){
                    SumTextView.setText("8");
                } else {
                    SumTextView.setText(history + "8");
                }
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history = SumTextView.getText().toString();
                if(history.equals("0") && history.length() == 1){
                    SumTextView.setText("9");
                } else {
                    SumTextView.setText(history + "9");
                }
            }
        });

        btn00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history = SumTextView.getText().toString();
                if(history.equals("0") && history.length() == 1){
                    SumTextView.setText("0");
                } else {
                    SumTextView.setText(history + "00");
                }
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history = SumTextView.getText().toString();
                if(history.equals("0") && history.length() == 1){
                    SumTextView.setText("0");
                } else {
                    SumTextView.setText(history + "0");
                }
            }
        });


        btnSetZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SumTextView.setText("0");
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                history = SumTextView.getText().toString();
                if(history.equals("0") || history.length() == 1){
                    SumTextView.setText("0");
                } else {
                    history = history.substring(0, history.length()-1);
                    SumTextView.setText(history);
                }
            }
        });

        btnFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SetDuthActivity.class);
                intent.putExtra("sum", SumTextView.getText().toString());
                intent.putExtra("finusernum", finusernum);
                intent.putExtra("name", dutchname);
                startActivity(intent);
            }
        });

    }

    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

}