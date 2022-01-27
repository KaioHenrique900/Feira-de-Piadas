package com.example.kaio;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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

public class TopPiadasAdapter extends RecyclerView.Adapter{
    Context context;
    List<MyItemPiada> itens;
    public TopPiadasAdapter(Context context, List<MyItemPiada> itens){
        this.context = context;
        this.itens = itens;
    }

    @NonNull

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.top_piadas_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyItemPiada myItem = itens.get(position);

        View v = holder.itemView;

        TextView tvPiada = v.findViewById(R.id.tvPiada4);
        tvPiada.setText(myItem.piada);

        TextView tvTitle = v.findViewById(R.id.tvTitle4);
        tvTitle.setText(myItem.titulo);

        TextView tvUser = v.findViewById(R.id.tvUser4);
        tvUser.setText(myItem.user);

        TextView countLikes = v.findViewById(R.id.countLikes);
        countLikes.setText(myItem.likes);

        TextView tvPiadaPosition = v.findViewById(R.id.tvPiadaPosition);
        tvPiadaPosition.setText(Integer.toString(position+1)+".");

        ImageButton buttonLike = v.findViewById(R.id.btnLikeTop);
        if (myItem.liked == 1){
            buttonLike.setImageResource(R.drawable.ic_twotone_sentiment_satisfied_alt__clicked_24);
        }
        else{
            buttonLike.setImageResource(R.drawable.ic_baseline_sentiment_satisfied_alt_24);
        }

        holder.itemView.findViewById(R.id.btnShareTop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, myItem.titulo+"\n\n"+myItem.piada+"\n\n"+"Piada publicada por: "+myItem.user);
                try {
                    context.startActivity(i);   //Tento executar a intenteção
                }
                catch (ActivityNotFoundException e){    //Caso não haja um App de e-mail, emito uma mensagem de erro
                    Toast.makeText(context, "Não há nenhuma aplicação de envio de texto instalada.", Toast.LENGTH_SHORT);
                }
            }
        });

        buttonLike = (ImageButton)holder.itemView.findViewById(R.id.btnLikeTop);
        ImageButton finalButtonLike = buttonLike;
        buttonLike.setOnClickListener(new View.OnClickListener() {
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
                                final int like = jsonObject.getInt("like");


                                ((Activity)context).runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        int likes;
                                        if (like == 1) {
                                            likes = Integer.parseInt(myItem.likes);
                                            likes+=1;
                                            myItem.likes = Integer.toString(likes);
                                            countLikes.setText(myItem.likes);

                                            finalButtonLike.setImageResource(R.drawable.ic_twotone_sentiment_satisfied_alt__clicked_24);
                                        } else {
                                            likes = Integer.parseInt(myItem.likes);
                                            likes-=1;
                                            myItem.likes = Integer.toString(likes);
                                            countLikes.setText(myItem.likes);

                                            finalButtonLike.setImageResource(R.drawable.ic_baseline_sentiment_satisfied_alt_24);

                                        }
                                    }
                                });

                            }

                            else{
                                final String error = jsonObject.getString("error");
                                final int like = jsonObject.getInt("like");
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
