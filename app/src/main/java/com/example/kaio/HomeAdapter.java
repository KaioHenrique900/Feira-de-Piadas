package com.example.kaio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kaio.fragment.HomeViewFragment;

import java.util.List;

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

        TextView tvUser = v.findViewById(R.id.tvUserName2);
        tvUser.setText(myItem.user);

        TextView tvPiada = v.findViewById(R.id.tvPiada2);
        tvUser.setText(myItem.piada);

        TextView tvTitle = v.findViewById(R.id.tvTitle2);
        tvUser.setText(myItem.titulo);
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }
}
