package com.example.haider.javaapi;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            // Add Basic Authentication headers
            String credentials = Credentials.basic("APIUSER", "Sail@123");

            httpClient.addInterceptor(chain -> {
                okhttp3.Request original = chain.request();
                okhttp3.Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", credentials)
                        .method(original.method(), original.body());
                okhttp3.Request request = requestBuilder.build();
                return chain.proceed(request);
            });

            // Add a logging interceptor to see request and response logs (optional)
            OkHttpClient client = httpClient
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.sail-steel.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
