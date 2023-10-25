package com.example.haider.javaapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.TimeZone;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Credentials;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    TextView txtResponse;
//    private static final String API_URL = "http://";
    private static final String USERNAME = "APIUSER";
    private static final String PASSWORD = "Sail@123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResponse=findViewById(R.id.txtResponse);
        // Create an OkHttpClient instance
        OkHttpClient client = new OkHttpClient();

//        String credentials = Credentials.basic(USERNAME, PASSWORD);
        Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show();

//        Request request = new Request.Builder()
//                .url(API_URL)
//                .header("Authorization", credentials)
//                .header("Content-Type","application/json")
//                .header("time-zone", TimeZone.getDefault().getID())
//                .header("Accept","application/json")
//                .header("X-Requested-With","X")
//                .build();
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        // Create the JSON data as a string
        String jsonData = "{" +
                "\"Opid\":\"OP14\"," +
                "\"QRCode\":\"https://madeinindia.qcin.org/product-details/e159952145ca436fad9bab058fa591e5/NB049264\"," +
                "\"HdrToItem\": []" +
                "}";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON, jsonData);

        Call<Void> call = apiService.performPostRequest(requestBody);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    int statusCode = response.code();
                    Log.d("API RESPONSE",statusCode+"");
                } else {
                    Log.d("API RESPONSE",response.toString());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("API RESPONSE",t.toString());
            }
        });
    }
}