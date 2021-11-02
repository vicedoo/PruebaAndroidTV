package com.example.pruebaandroidtv.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pruebaandroidtv.R;
import com.example.pruebaandroidtv.main.Contenido;
import com.example.pruebaandroidtv.main.User;

import java.util.List;


public class MyContenidoRecyclerViewAdapter extends RecyclerView.Adapter<MyContenidoRecyclerViewAdapter.ViewHolder> {

    private final List<Contenido> mValues;
    private Context context;
    private User user;


    public MyContenidoRecyclerViewAdapter(Context context, List<Contenido> items) {
        mValues = items;
        this.context = context;
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

        if (!holder.mItem.getUrl().equals("")){
            Glide.with(context).
                    load(holder.mItem.getUrl()).into(holder.ivPortada);
        }

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView ivPortada, ivLike;
        public Contenido mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ivPortada = (ImageView) view.findViewById(R.id.ivPortada);
            ivLike = (ImageView) view.findViewById(R.id.ivLike);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + "'";
        }
    }
}