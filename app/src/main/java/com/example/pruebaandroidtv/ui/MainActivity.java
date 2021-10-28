package com.example.pruebaandroidtv.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pruebaandroidtv.R;
import com.example.pruebaandroidtv.conection.AppClient;
import com.example.pruebaandroidtv.conection.RetrofitService;
import com.example.pruebaandroidtv.login.ParamsLogin;
import com.example.pruebaandroidtv.login.ResponseLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public Button btnLogin;
    public EditText etEmail, etPassword;
    public AppClient appClient;
    public RetrofitService retrofitService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofitInit();
        findViews();
        events();

    }

    public void retrofitInit(){

        appClient = AppClient.getInstance();
        retrofitService = appClient.getRetrofitService();

    }

    public void findViews(){
        btnLogin = findViewById(R.id.buttonLogin);
        etEmail = findViewById(R.id.editTextUser);
        etPassword = findViewById(R.id.editTextPass);
    }

    public void events() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                if (email.isEmpty()){
                    etEmail.setError("El email es requerido");
                } else if(password.isEmpty())
                {
                    etPassword.setError("La contraseña es requerida");
                }
                else{
                    ParamsLogin paramsLogin = new ParamsLogin(email, password);

                    Call<ResponseLogin> call = retrofitService.doLogin(paramsLogin);
                    call.enqueue(new Callback<ResponseLogin>() {
                        @Override
                        public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                            if (response.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Sesion iniciada correctamente", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseLogin> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Problemas de conexión", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });
    }

}