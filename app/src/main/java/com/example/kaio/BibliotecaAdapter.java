package com.example.kaio;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kaio.util.Util;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BibliotecaAdapter extends RecyclerView.Adapter{
    @NonNull
    @NotNull

    Context context;
    List<MyItemBiblioteca> itens;
    public BibliotecaAdapter(Context context, List<MyItemBiblioteca> itens){
        this.context = context;
        this.itens = itens;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.biblioteca_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        MyItemBiblioteca myItem = itens.get(position);

        View v = holder.itemView;

        TextView tvCat = v.findViewById(R.id.tvCat);
        tvCat.setText(myItem.nomeCategoria);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PiadasCategoriaActivity.class);
                i.setAction(Intent.ACTION_SEND);
                i.putExtra("nomeCategoria", myItem.nomeCategoria);
                i.setType("text/plain");
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }
}
