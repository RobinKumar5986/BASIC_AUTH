package com.example.haider.javaapi;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("qrcode/QRCode_FetchSet?sap-client=600")
    Call<Void> performPostRequest(@Body RequestBody request);
}
