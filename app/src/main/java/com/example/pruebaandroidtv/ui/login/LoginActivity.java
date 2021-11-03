package com.example.pruebaandroidtv.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserView;

import com.example.pruebaandroidtv.R;
import com.example.pruebaandroidtv.common.Constantes;
import com.example.pruebaandroidtv.common.Encrypt;
import com.example.pruebaandroidtv.common.SharedPreferencesManager;
import com.example.pruebaandroidtv.conection.AppClient;
import com.example.pruebaandroidtv.conection.RetrofitService;
import com.example.pruebaandroidtv.login.ResponseLogin;
import com.example.pruebaandroidtv.ui.main.MainActivity;

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
        checkSharedPreferences();
        retrofitInit();
        findViews();
        events();

    }

    private void checkSharedPreferences(){
       if(SharedPreferencesManager.getSomeStringValue(Constantes.PREF_TOKEN)==null || SharedPreferencesManager.getSomeStringValue(Constantes.PREF_TOKEN).isEmpty()){

       }else{
           Intent intent = new Intent(LoginActivity.this, MainActivity.class);
           startActivity(intent);
           finish();
        }

    }

    private void retrofitInit(){

        appClient = AppClient.getInstance();
        retrofitService = appClient.getRetrofitService();

    }

    private void findViews(){
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
                String device = Constantes.ANDROID_DEVICE;
                if (email.isEmpty()){
                    etEmail.setError("El email es requerido");
                } else if(password.isEmpty())
                {
                    etPassword.setError("La contraseña es requerida");
                }
                else{
                    //ParamsLogin paramsLogin = new ParamsLogin(email, password, device);


                    if(!email.equals(Constantes.EMAIL_CORRECTO)  || !password.equals(Constantes.PASS_CORRECTO) ){

                        Toast.makeText(LoginActivity.this, "Email o contraseña incorrectos", Toast.LENGTH_SHORT).show();

                    }else{

                        password = Encrypt.md5(password);
                        Call<ResponseLogin> call = retrofitService.doLogin(email, password, device);
                        call.enqueue(new Callback<ResponseLogin>() {
                            @Override
                            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                                if (response.isSuccessful()){
                                    Toast.makeText(LoginActivity.this, "Sesion iniciada correctamente", Toast.LENGTH_SHORT).show();
                                    SharedPreferencesManager.setSomeStringValue(Constantes.PREF_TOKEN, response.body().getToken());
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                                Toast.makeText(LoginActivity.this, "Problemas de conexión", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }

            }
        });
    }

}