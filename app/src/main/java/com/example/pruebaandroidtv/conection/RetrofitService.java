package com.example.pruebaandroidtv.conection;

import com.example.pruebaandroidtv.login.ParamsLogin;
import com.example.pruebaandroidtv.login.ResponseLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitService {

    @POST("Login.php")
    Call<ResponseLogin> doLogin(@Body ParamsLogin paramsLogin);

}
