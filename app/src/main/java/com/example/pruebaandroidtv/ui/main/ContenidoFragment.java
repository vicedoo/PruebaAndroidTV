package com.example.pruebaandroidtv.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebaandroidtv.R;
import com.example.pruebaandroidtv.common.Constantes;
import com.example.pruebaandroidtv.common.SharedPreferencesManager;
import com.example.pruebaandroidtv.conection.AppClient;
import com.example.pruebaandroidtv.conection.RetrofitService;
import com.example.pruebaandroidtv.main.Contenido;
import com.example.pruebaandroidtv.main.ResponseGetView;
import com.example.pruebaandroidtv.main.User;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 */
public class ContenidoFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private List<Contenido> contenidoList;
    private MyContenidoRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    public User user;
    public Contenido[] contenidos;
    public RetrofitService retrofitService;
    public AppClient appClient;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ContenidoFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ContenidoFragment newInstance(int columnCount) {
        ContenidoFragment fragment = new ContenidoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contenido_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            retrofitInit();
            actualizarContenido();
        }
        return view;
    }

    private void retrofitInit(){

        appClient = AppClient.getInstance();
        retrofitService = appClient.getRetrofitService();

    }

    private void actualizarContenido(){

        String device = Constantes.ANDROID_DEVICE;

        String token = SharedPreferencesManager.getSomeStringValue(Constantes.PREF_TOKEN);

        Call<ResponseGetView> call = retrofitService.doGetView(token, device);
        call.enqueue(new Callback<ResponseGetView>() {
            @Override
            public void onResponse(Call<ResponseGetView> call, Response<ResponseGetView> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getActivity(), "Conexion correcta", Toast.LENGTH_SHORT).show();

                    setClases(response);
                    adapter = new MyContenidoRecyclerViewAdapter(getActivity(), contenidoList);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<ResponseGetView> call, Throwable t) {
                Toast.makeText(getActivity(), "Problemas de conexión", Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void setClases(Response<ResponseGetView> response){
        user = response.body().getUser();
        contenidos = response.body().getContenidos();
        contenidoList = Arrays.asList(contenidos);
    }
}