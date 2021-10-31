package com.example.pruebaandroidtv.ui.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pruebaandroidtv.R;
import com.example.pruebaandroidtv.common.Encrypt;
import com.example.pruebaandroidtv.conection.AppClient;
import com.example.pruebaandroidtv.conection.RetrofitService;
import com.example.pruebaandroidtv.login.ResponseLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    public Button btnLogin;
    public EditText etEmail, etPassword;
    public AppClient appClient;
    public RetrofitService retrofitService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

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
                String device = "Android";
                if (email.isEmpty()){
                    etEmail.setError("El email es requerido");
                } else if(password.isEmpty())
                {
                    etPassword.setError("La contraseña es requerida");
                }
                else{
                    //ParamsLogin paramsLogin = new ParamsLogin(email, password, device);

                    password = Encrypt.md5(password);
                    Log.i("CONTRASEÑA", password);

                    Call<ResponseLogin> call = retrofitService.doLogin(email, password, device);
                    call.enqueue(new Callback<ResponseLogin>() {
                        @Override
                        public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                            if (response.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Sesion iniciada correctamente", Toast.LENGTH_SHORT).show();




                                Toast.makeText(LoginActivity.this, "Prueba  " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                String token = "" + response.body().getToken();
                                Log.i("MENSAJE LOG", token);
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseLogin> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "Problemas de conexión", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });
    }

}