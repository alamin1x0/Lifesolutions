package com.lifesolutions.bd.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientForCourse {

    // private static final String BASE_URL = "http://192.168.1.108:8201/";
    private static final String BASE_URL = "https://api.course.starnotesocial.com/api/course/";
    private static RetrofitClientForCourse mInstance;
    private Retrofit retrofit;

    private RetrofitClientForCourse() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClientForCourse getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClientForCourse();
        }
        return mInstance;
    }

    public ServerApi getServerApi() {
        return retrofit.create(ServerApi.class);
    }


/*    public static Retrofit RETROFIT = null;

    public static Retrofit getClient() {

        if (RETROFIT == null) {

            //For generating request
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();


            Gson gson = new GsonBuilder().create();

            RETROFIT = new Retrofit.Builder().baseUrl("https://api.course.starnotesocial.com/api/course/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();


        }
        return RETROFIT;
    }*/

}
