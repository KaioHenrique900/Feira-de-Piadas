package com.example.kaio;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kaio.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeAdapter extends RecyclerView.Adapter {
    Context context;
    List<MyItemPiada> itens;
    public HomeAdapter(Context context, List<MyItemPiada> itens){
        this.context = context;
        this.itens = itens;
    }

    @NonNull

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.home_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyItemPiada myItem = itens.get(position);

        View v = holder.itemView;

        TextView tvUser = v.findViewById(R.id.tvUserHome);
        tvUser.setText(myItem.user);

        TextView tvPiada = v.findViewById(R.id.tvPiadaHome);
        tvPiada.setText(myItem.piada);

        TextView tvTitle = v.findViewById(R.id.tvTitleHome);
        tvTitle.setText(myItem.titulo);

        holder.itemView.findViewById(R.id.btnLikeHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL_BASE + "like_piada.php", "POST", "UTF-8");
                        httpRequest.addParam("titlePiada", myItem.titulo.toString());
                        httpRequest.addParam("email", Config.getLogin(context).toString());
                        try{
                            InputStream is = httpRequest.execute();
                            String result = Util.inputStream2String(is, "UTF-8");
                            httpRequest.finish();

                            JSONObject jsonObject = new JSONObject(result);
                            final int success = jsonObject.getInt("success");
                            if(success == 1){
                                ((HomeActivity)context).runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        int color = Color.parseColor("#AE6118"); //The color u want
                                        v.setBackgroundResource(R.drawable.ic_baseline_sentiment_satisfied_alt_24_clicked);
                                    }
                                });
                            }
                            else{
                                final String error = jsonObject.getString("error");
                                ((HomeActivity)context).runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(context, error, Toast.LENGTH_LONG).show();
                                        v.setEnabled(true);
                                    }
                                });
                            }
                        } catch(IOException | JSONException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }
}
