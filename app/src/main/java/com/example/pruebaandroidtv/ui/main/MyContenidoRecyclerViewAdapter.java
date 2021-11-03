package com.example.pruebaandroidtv.ui.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pruebaandroidtv.R;
import com.example.pruebaandroidtv.common.Constantes;
import com.example.pruebaandroidtv.main.Contenido;
import com.example.pruebaandroidtv.main.User;
import com.example.pruebaandroidtv.ui.player.PlayerActivity;

import org.w3c.dom.Text;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;


public class MyContenidoRecyclerViewAdapter extends RecyclerView.Adapter<MyContenidoRecyclerViewAdapter.ViewHolder> {

    private final List<Contenido> mValues;
    private Context context;
    private User user;
    private List<String> favs;


    public MyContenidoRecyclerViewAdapter(Context context, List<Contenido> items, User user) {
        mValues = items;
        this.context = context;
        this.user = user;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_contenido, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tvTitulo.setText(holder.mItem.getTitle());

        if (!holder.mItem.getUrl().equals("")){
            Glide.with(context).
                    load(holder.mItem.getCover()).fitCenter().into(holder.ivPortada);
        }

        for( String id: user.getFavs()){
            if (id.equals(holder.mItem.getId())){
                Glide.with(context).load(R.drawable.ic_like_pink)
                        .into(holder.ivLike);
                break;
            }

        }

        // LIKES NO FUNCIONA

        /*holder.ivLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(favs.contains(holder.mItem.getId())){
                    Glide.with(context).load(R.drawable.ic_like)
                            .into(holder.ivLike);
                }else {
                    Glide.with(context).load(R.drawable.ic_like_pink)
                            .into(holder.ivLike);
                }
            }
        });*/

        holder.ivPortada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, PlayerActivity.class);
                intent.putExtra(Constantes.PARAMSPLAYER_ID, holder.mItem.getId());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView ivPortada, ivLike;
        public final TextView tvTitulo;
        public Contenido mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ivPortada = (ImageView) view.findViewById(R.id.ivPortada);
            ivLike = (ImageView) view.findViewById(R.id.ivLike);
            tvTitulo = (TextView) view.findViewById(R.id.tvTitulo);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + "'";
        }
    }
}