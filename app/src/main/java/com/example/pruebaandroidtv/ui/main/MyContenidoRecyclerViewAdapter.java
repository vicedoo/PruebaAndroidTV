package com.example.pruebaandroidtv.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebaandroidtv.R;
import com.example.pruebaandroidtv.main.Contenido;

import java.util.List;


public class MyContenidoRecyclerViewAdapter extends RecyclerView.Adapter<MyContenidoRecyclerViewAdapter.ViewHolder> {

    private final List<Contenido> mValues;
    private Context context;

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
        holder.tvUsername.setText(mValues.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvUsername;
        public Contenido mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvUsername = (TextView) view.findViewById(R.id.textViewContenidoTitulo);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvUsername.getText() + "'";
        }
    }
}