package com.lifesolutions.bd.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    // private static final String BASE_URL = "http://192.168.1.108:8201/";
    private static final String BASE_URL = "https://ftp.starnotesocial.com/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public ServerApi getServerApi() {
        return retrofit.create(ServerApi.class);
    }
}
