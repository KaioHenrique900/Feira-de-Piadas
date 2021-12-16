package com.example.kaio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.kaio.model.HomeViewModel;
import com.example.kaio.model.PerfilUserViewModel;
import com.example.kaio.model.PerfilViewModel;

import java.util.List;

public class PerfilUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_user);

        ImageButton imVoltar = findViewById(R.id.imgBtnVoltar1);
        imVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PerfilUserActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });

        PerfilUserViewModel vm = new ViewModelProvider(this).get(PerfilUserViewModel.class);
        List<MyItemPiada> itens = vm.getItens();

        PerfilUserAdapter perfilUserAdapter = new PerfilUserAdapter(this, itens);

        perfilUserAdapter = new PerfilUserAdapter(this, itens);

        MyItemPiada newPiada = new MyItemPiada();
        newPiada.titulo = "Piada Titulo";
        newPiada.piada = "Uma Piadoca djakfjakfjakfjakfjakfajkfjakfjakfjakfjakfjakfajkfajfka";

        itens.add(newPiada);

        newPiada.titulo = "Piada Titulo";
        newPiada.piada = "Uma Piadoca fajfkajfkajfkajfkafjakfjafkjakajfkajakfjakafjakajfkajakj";

        itens.add(newPiada);

        newPiada.titulo = "Piada Titulo";
        newPiada.piada = "Uma Piadoca fajfkajfkajfkajfkafjakfjafkjakajfkajakfjakafjakajfkajakj";

        itens.add(newPiada);

        perfilUserAdapter.notifyItemInserted(itens.size()-1);
        RecyclerView rvPerfilUser = findViewById(R.id.rvPerfilUser);
        rvPerfilUser.setLayoutManager(new LinearLayoutManager(this));
        rvPerfilUser.setAdapter(perfilUserAdapter);
    }
}