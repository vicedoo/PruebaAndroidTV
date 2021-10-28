package com.example.pruebaandroidtv.conection;

import com.example.pruebaandroidtv.common.Constantes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppClient {

    private static AppClient instance = null;
    private RetrofitService retrofitService;
    private Retrofit retrofit;

    public AppClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitService = retrofit.create(RetrofitService.class);
    }

    public static AppClient getInstance(){
        if (instance == null){
            instance = new AppClient();
        }
        return instance;
    }

    public RetrofitService getRetrofitService(){
        return retrofitService;
    }


}
