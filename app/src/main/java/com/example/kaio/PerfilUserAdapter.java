package com.example.kaio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PerfilUserAdapter extends RecyclerView.Adapter {
    Context context;
    List<MyItemPiada> itens;
    public PerfilUserAdapter(Context context, List<MyItemPiada> itens){
        this.context = context;
        this.itens = itens;
    }

    @NonNull

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.perfil_piada_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyItemPiada myItem = itens.get(position);

        View v = holder.itemView;

        TextView tvPiada = v.findViewById(R.id.tvPiadaPerfilUser);
        tvPiada.setText(myItem.piada);

        TextView tvTitle = v.findViewById(R.id.tvTitlePerfilUser);
        tvTitle.setText(myItem.titulo);
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }
}
