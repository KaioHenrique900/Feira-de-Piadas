package com.example.kaio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kaio.model.PerfilUserViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class PerfilActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);



        Bundle extras = getIntent().getExtras();

        TextView tvPerfil = findViewById(R.id.tvPerfil);
        tvPerfil.setText(extras.getString("Username"));

        PerfilUserViewModel vm = new ViewModelProvider(this).get(PerfilUserViewModel.class);
        List<MyItemPiada> itens = vm.getItens();

        PerfilUserAdapter perfilUserAdapter = new PerfilUserAdapter(this, itens);

        MyItemPiada newPiada = new MyItemPiada();
        newPiada.titulo = "Pedreiro Japonês";
        newPiada.piada = "Como se chama pedreiro no Japão\nTaca massa no muro";

        itens.add(newPiada);

        newPiada = new MyItemPiada();
        newPiada.titulo = "Buuu!";
        newPiada.piada = "Por que é proibido assustar as pesoas na Hungria?\nPorque o 'Buuu!' dá peste";

        itens.add(newPiada);

        newPiada = new MyItemPiada();
        newPiada.titulo = "Xadrez Ianque";
        newPiada.piada = "Por que não dá pra jogar xadrez nos EUA?\nPorque estão faltando duas torres";

        itens.add(newPiada);

        perfilUserAdapter.notifyItemInserted(itens.size()-1);
        RecyclerView rvPerfil = findViewById(R.id.rvPerfil);
        rvPerfil.setLayoutManager(new LinearLayoutManager(this));
        rvPerfil.setAdapter(perfilUserAdapter);

        ImageButton imVoltar = findViewById(R.id.imgBtnVoltar1);
        imVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PerfilActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
    }
}