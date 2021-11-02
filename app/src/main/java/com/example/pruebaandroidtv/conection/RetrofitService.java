package com.example.pruebaandroidtv.conection;

import com.example.pruebaandroidtv.login.ResponseLogin;
import com.example.pruebaandroidtv.main.ResponseGetView;
import com.example.pruebaandroidtv.player.ResponsePlay;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitService {
    @FormUrlEncoded
    @POST("Login.php")
    Call<ResponseLogin> doLogin(@Field("user") String user, @Field("pass") String pass, @Field("device") String device);

    @FormUrlEncoded
    @POST("GetView.php")
    Call<ResponseGetView> doGetView(@Field("token") String token, @Field("device") String device);

    @FormUrlEncoded
    @POST("Play.php")
    Call<ResponsePlay> doPlay(@Field("token") String token, @Field("device") String device, @Field("id") String id);

}
