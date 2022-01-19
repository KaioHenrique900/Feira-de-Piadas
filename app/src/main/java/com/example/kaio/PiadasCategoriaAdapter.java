package com.example.kaio;

import android.content.ActivityNotFoundException;
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

public class PiadasCategoriaAdapter extends RecyclerView.Adapter{
    Context context;
    List<MyItemPiada> itens;
    public PiadasCategoriaAdapter(Context context, List<MyItemPiada> itens){
        this.context = context;
        this.itens = itens;
    }

    @NonNull

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.piadas_categoria_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyItemPiada myItem = itens.get(position);

        View v = holder.itemView;

        TextView tvUser = v.findViewById(R.id.tvUserPiadaCategoria);
        tvUser.setText(myItem.user);

        TextView tvPiada = v.findViewById(R.id.tvPiadaCategoria);
        tvPiada.setText(myItem.piada);

        TextView tvTitle = v.findViewById(R.id.tvTitlePiadaCategoria);
        tvTitle.setText(myItem.titulo);

        holder.itemView.findViewById(R.id.tvUserPiadaCategoria).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PerfilActivity.class);
                i.setAction(Intent.ACTION_SEND);
                i.putExtra("Username", myItem.user);
                i.setType("text/plain");
                context.startActivity(i);
            }
        });

        holder.itemView.findViewById(R.id.btnSharePC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, myItem.titulo+"\n\n"+myItem.piada+"\n\n"+"Piada publicada por: "+myItem.user);
                try {
                    context.startActivity(i);   //Tento executar a intenteção
                }
                catch (ActivityNotFoundException e){    //Caso não haja um App de e-mail, emito uma mensagem de erro
                    Toast.makeText(context, "Não há nenhuma aplicação de e-mail instalada.", Toast.LENGTH_SHORT);
                }
            }
        });
        
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }
}