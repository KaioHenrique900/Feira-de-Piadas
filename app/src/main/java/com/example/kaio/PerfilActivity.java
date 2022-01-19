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

        bottomNavigationView = findViewById(R.id.btNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.HomeViewOp:
                        /*HomeViewFragment homeViewFragment = HomeViewFragment.newInstance();
                        setFragment(homeViewFragment);*/
                        break;
                    case R.id.bibliotecaViewOp:
                        /*BibliotecaViewFragment bibliotecaViewFragment = BibliotecaViewFragment.newInstance();
                        setFragment(bibliotecaViewFragment);*/
                        break;
                    case R.id.topPiadasViewOp:
                        /*TopPiadasViewFragment topPiadasViewFragment = TopPiadasViewFragment.newInstance();
                        setFragment(topPiadasViewFragment);*/
                        break;
                }
                return true;
            }
        });

        Bundle extras = getIntent().getExtras();

        TextView tvPerfil = findViewById(R.id.tvPerfil);
        tvPerfil.setText(extras.getString("Username"));

        PerfilUserViewModel vm = new ViewModelProvider(this).get(PerfilUserViewModel.class);
        List<MyItemPiada> itens = vm.getItens();

        PerfilUserAdapter perfilUserAdapter = new PerfilUserAdapter(this, itens);

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