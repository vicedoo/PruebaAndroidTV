package com.example.pruebaandroidtv.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.pruebaandroidtv.R;
import com.example.pruebaandroidtv.common.Constantes;
import com.example.pruebaandroidtv.common.SharedPreferencesManager;
import com.example.pruebaandroidtv.conection.AppClient;
import com.example.pruebaandroidtv.conection.RetrofitService;
import com.example.pruebaandroidtv.login.ResponseLogin;
import com.example.pruebaandroidtv.main.Contenido;
import com.example.pruebaandroidtv.main.ResponseGetView;
import com.example.pruebaandroidtv.main.User;
import com.example.pruebaandroidtv.ui.login.LoginActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pruebaandroidtv.common.MyApp.getContext;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private List<Contenido> contenidoList;
    private MyContenidoRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private User user;
    private RetrofitService retrofitService;
    private AppClient appClient;
    private SearchView searchView;
    private String device = Constantes.ANDROID_DEVICE;
    private String token = SharedPreferencesManager.getSomeStringValue(Constantes.PREF_TOKEN);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvContenido);
        searchView = findViewById(R.id.svBuscador);

        searchView.setOnQueryTextListener(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        retrofitInit();
        actualizarContenido();


    }


    private void retrofitInit(){

        appClient = AppClient.getInstance();
        retrofitService = appClient.getRetrofitService();

    }

    private void actualizarContenido(){

        Call<ResponseGetView> call = retrofitService.doGetView(token, device);
        call.enqueue(new Callback<ResponseGetView>() {
            @Override
            public void onResponse(Call<ResponseGetView> call, Response<ResponseGetView> response) {
                if (response.isSuccessful()){
                    setClases(response);
                    adapter = new MyContenidoRecyclerViewAdapter(MainActivity.this, contenidoList, user);
                    recyclerView.setAdapter(adapter);

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
        contenidoList = response.body().getContenidos();
    }


    private void searchByName(String query){

        contenidoList.clear();

        Call<ResponseGetView> call = retrofitService.doGetView(token, device);
        call.enqueue(new Callback<ResponseGetView>() {
            @Override
            public void onResponse(Call<ResponseGetView> call, Response<ResponseGetView> response) {
                if (response.isSuccessful()){
                    for (Contenido contenido:response.body().getContenidos()) {

                        if (contenido.getTitle().toLowerCase().contains(query)){
                            contenidoList.add(contenido);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetView> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Problemas de conexión", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        if (query!=null || !query.isEmpty()){
            searchByName(query.toLowerCase());
        }
        if(query.isEmpty()){
            Call<ResponseGetView> call = retrofitService.doGetView(token, device);
            call.enqueue(new Callback<ResponseGetView>() {
                @Override
                public void onResponse(Call<ResponseGetView> call, Response<ResponseGetView> response) {
                    if (response.isSuccessful()){
                        contenidoList = response.body().getContenidos();
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<ResponseGetView> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Problemas de conexión", Toast.LENGTH_SHORT).show();
                }
            });
        }

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }
}