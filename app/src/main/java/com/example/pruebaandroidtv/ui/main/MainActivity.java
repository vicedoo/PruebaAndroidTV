package com.example.pruebaandroidtv.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pruebaandroidtv.R;
import com.example.pruebaandroidtv.common.Constantes;
import com.example.pruebaandroidtv.conection.AppClient;
import com.example.pruebaandroidtv.conection.RetrofitService;
import com.example.pruebaandroidtv.login.ResponseLogin;
import com.example.pruebaandroidtv.main.Contenido;
import com.example.pruebaandroidtv.main.ResponseGetView;
import com.example.pruebaandroidtv.main.User;
import com.example.pruebaandroidtv.ui.login.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public RetrofitService retrofitService;
    public AppClient appClient;
    public String token;
    public User user;
    public Contenido[] contenidos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        token = bundle.getString("token");

        retrofitInit();
        endPointMain();

    }

    private void retrofitInit(){

        appClient = AppClient.getInstance();
        retrofitService = appClient.getRetrofitService();

    }

    private void endPointMain(){

        String device = Constantes.ANDROID_DEVICE;

        Call<ResponseGetView> call = retrofitService.doGetView(token, device);
        call.enqueue(new Callback<ResponseGetView>() {
            @Override
            public void onResponse(Call<ResponseGetView> call, Response<ResponseGetView> response) {
                if (response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Conexion correcta", Toast.LENGTH_SHORT).show();

                    setClases(response);

                }
            }

            @Override
            public void onFailure(Call<ResponseGetView> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Problemas de conexión", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setClases(Response<ResponseGetView> response){
        user = response.body().getUser();
        contenidos = response.body().getContenidos();
    }

}