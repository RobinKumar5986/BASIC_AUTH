package com.example.haider.javaapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.TimeZone;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Credentials;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    TextView txtResponse;
    private static final String API_URL = "http://";
    private static final String USERNAME = "APIUSER";
    private static final String PASSWORD = "Sail@123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResponse=findViewById(R.id.txtResponse);
        // Create an OkHttpClient instance
        OkHttpClient client = new OkHttpClient();

        String credentials = Credentials.basic(USERNAME, PASSWORD);
        Toast.makeText(this, credentials, Toast.LENGTH_SHORT).show();

//        Request request = new Request.Builder()
//                .url(API_URL)
//                .header("Authorization", credentials)
//                .header("Content-Type","application/json")
//                .header("time-zone", TimeZone.getDefault().getID())
//                .header("Accept","application/json")
//                .header("X-Requested-With","X")
//                .build();
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyApiService apiService = retrofit.create(MyApiService.class);

        MyData requestData = new MyData("value1", "value2");
        Call<YourResponseModel> modelCall;
    }
}