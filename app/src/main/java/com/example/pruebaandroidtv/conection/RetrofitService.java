package com.example.pruebaandroidtv.conection;

import com.example.pruebaandroidtv.common.Constantes;
import com.example.pruebaandroidtv.login.ParamsLogin;
import com.example.pruebaandroidtv.login.ResponseLogin;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface RetrofitService {
    @FormUrlEncoded
    @POST("Login.php")
    Call<ResponseLogin> doLogin(@Field("user") String user, @Field("pass") String pass, @Field("device") String device);

}
