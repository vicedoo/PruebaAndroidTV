package com.example.pruebaandroidtv.ui.player;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.pruebaandroidtv.R;
import com.example.pruebaandroidtv.common.Constantes;
import com.example.pruebaandroidtv.common.SharedPreferencesManager;
import com.example.pruebaandroidtv.conection.AppClient;
import com.example.pruebaandroidtv.conection.RetrofitService;
import com.example.pruebaandroidtv.player.ResponsePlay;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PlayerActivity extends AppCompatActivity {

    public RetrofitService retrofitService;
    public AppClient appClient;
    public ResponsePlay responsePlay;
    public String token, device, id;
    public TextView tvId, tvTitle, tvCover, tvUrl;
    public VideoView vvVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        getSupportActionBar().hide();

        retrofitInit();
        params();
        callPlay();

    }
    private void retrofitInit(){

        appClient = AppClient.getInstance();
        retrofitService = appClient.getRetrofitService();

    }

    private void params(){
        token = SharedPreferencesManager.getSomeStringValue(Constantes.PREF_TOKEN);
        device = Constantes.ANDROID_DEVICE;
        Bundle bundle = getIntent().getExtras();
        id = bundle.getString(Constantes.PARAMSPLAYER_ID);
    }

    private void callPlay(){

        Call<ResponsePlay> call = retrofitService.doPlay(token, device, id);
        call.enqueue(new Callback<ResponsePlay>() {
            @Override
            public void onResponse(Call<ResponsePlay> call, Response<ResponsePlay> response) {
                if (response.isSuccessful()){
                    responsePlay = response.body();
                    videoSettings();
                }
            }

            @Override
            public void onFailure(Call<ResponsePlay> call, Throwable t) {
                Toast.makeText(PlayerActivity.this, "Problemas de conexion", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void videoSettings(){


        vvVideo = findViewById(R.id.vvVideo);
        vvVideo.setVideoPath(responsePlay.getUrl());

        MediaController mediaController = new MediaController(this);
        vvVideo.setMediaController(mediaController);

        vvVideo.start();

    }
}