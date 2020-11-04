package com.chlwhdtn.smaretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button upload_btn, view_btn;
    EditText edit_name, edit_number, view_number;
    TextView edit_result, view_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        upload_btn = findViewById(R.id.edit_upload);
        view_btn = findViewById(R.id.view_btn);

        edit_name=findViewById(R.id.edit_name);
        edit_number=findViewById(R.id.edit_number);
        view_number=findViewById(R.id.view_number);

        edit_result=findViewById(R.id.upload_result);
        view_result=findViewById(R.id.view_result);

        upload_btn.setOnClickListener(new View.OnClickListener() { // 학번 수정
            @Override
            public void onClick(View view) {
                edit_result.setText("");
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("https://sma-student.run.goorm.io/")
                        .build();

                RetrofitService service = retrofit.create(RetrofitService.class);

                int number;
                try {
                    number = Integer.parseInt(edit_number.getText().toString());
                } catch(NumberFormatException e) {
                    edit_result.setText("학번에 숫자형식으로 넣어주세요");
                    return;
                }
                String name = edit_name.getText().toString();
                service.setStudent(number, name).enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> response) {
                        edit_result.setText(response.body().getMessage());
                    }

                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {
                        edit_result.setText(t.getMessage() + " / " + t.getStackTrace()[0].getLineNumber());
                    }
                });

            }
        });

        view_btn.setOnClickListener(new View.OnClickListener() { // 학번 조회
            @Override
            public void onClick(View view) {
                view_result.setText("");
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("https://sma-student.run.goorm.io/")
                        .build();

                RetrofitService service = retrofit.create(RetrofitService.class);

                int number;
                try {
                    number = Integer.parseInt(view_number.getText().toString());
                } catch(NumberFormatException e) {
                    view_result.setText("학번에 숫자형식으로 넣어주세요");
                    return;
                }

                service.getStudent(number).enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> response) {
                        view_result.setText(response.body().getMessage());
                    }

                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {
                        view_result.setText(t.getMessage() + " / " + t.getStackTrace()[0].getLineNumber());
                    }
                });

            }
        });

    }
}