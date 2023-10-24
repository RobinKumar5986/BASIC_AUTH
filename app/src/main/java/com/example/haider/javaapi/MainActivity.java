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


public class MainActivity extends AppCompatActivity {
    TextView txtResponse;
    private static final String API_URL = "http://www.sail-steel.com/qrcode/QRCode_FetchSet?sap-client=600"; // Replace with your API endpoint
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

        Request request = new Request.Builder()
                .url(API_URL)
                .header("Authorization", credentials)
                .header("Content-Type","application/json")
                .header("time-zone", TimeZone.getDefault().getID())
                .header("Accept","application/json")
                .header("X-Requested-With","X")
                .build();

        // Execute the request in a background thread
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e("OkHttp", "Request failed: " + e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    Log.e("OkHttp", response.toString());
            }
        });
    }
}