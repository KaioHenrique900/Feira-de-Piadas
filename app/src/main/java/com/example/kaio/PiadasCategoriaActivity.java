package com.example.kaio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kaio.model.HomeViewModel;
import com.example.kaio.model.PerfilUserViewModel;
import com.example.kaio.model.PiadasCategoriaViewModel;

import java.util.List;

public class PiadasCategoriaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piadas_categoria);

        ImageButton imVoltar = findViewById(R.id.imgBtnVoltar);
        imVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PiadasCategoriaActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
        Bundle extras = getIntent().getExtras();

        TextView tvPCTitle = findViewById(R.id.tvPCTitle);
        tvPCTitle.setText(extras.getString("nomeCategoria"));

        PiadasCategoriaViewModel piadasCategoriaViewModel = new ViewModelProvider(this).get(PiadasCategoriaViewModel.class);
        piadasCategoriaViewModel.setCategoria(extras.getString("nomeCategoria"));

        RecyclerView rvPiadasCategoria = findViewById(R.id.rvPiadasCategoria);
        rvPiadasCategoria.setHasFixedSize(true);
        rvPiadasCategoria.setLayoutManager(new LinearLayoutManager(this));

        LiveData<List<MyItemPiada>> piadas = piadasCategoriaViewModel.getPiadas();
        piadas.observe(PiadasCategoriaActivity.this, new Observer<List<MyItemPiada>>() {
            @Override
            public void onChanged(List<MyItemPiada> piadas) {
                PiadasCategoriaAdapter piadasCategoriaAdapter = new PiadasCategoriaAdapter(PiadasCategoriaActivity.this, piadas);
                rvPiadasCategoria.setAdapter(piadasCategoriaAdapter);
            }
        });

    }
}