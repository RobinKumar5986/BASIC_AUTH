package com.example.haider.javaapi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyApiService {
    @Headers({
            "Authorization: Basic QVBJVVNFUjpTYWlsQDEyMw==",
            "Accept: application/json ",
            "X-Requested-With: X"
    })
    @POST("www.sail-steel.com/qrcode/QRCode_FetchSet?sap-client=600")
    Call<YourResponseModel> postData(@Body MyData data);
}
