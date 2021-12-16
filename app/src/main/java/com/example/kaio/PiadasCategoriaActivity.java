package com.example.kaio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

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

        PiadasCategoriaViewModel vm = new ViewModelProvider(this).get(PiadasCategoriaViewModel.class);
        List<MyItemPiada> itens = vm.getItens();

        PiadasCategoriaAdapter piadasCategoriaAdapter = new PiadasCategoriaAdapter(this, itens);

        MyItemPiada newPiada = new MyItemPiada();
        newPiada.user = "Kaio";
        newPiada.titulo = "Piada Titulo";
        newPiada.piada = "Uma Piadoca djakfjakfjakfjakfjakfajkfjakfjakfjakfjakfjakfajkfajfka";

        itens.add(newPiada);

        newPiada = new MyItemPiada();
        newPiada.user = "Kaio";
        newPiada.titulo = "Piada Titulo";
        newPiada.piada = "Uma Piadoca djakfjakfjakfjakfjakfajkfjakfjakfjakfjakfjakfajkfajfka";

        itens.add(newPiada);

        newPiada = new MyItemPiada();
        newPiada.user = "Kaio";
        newPiada.titulo = "Piada Titulo";
        newPiada.piada = "Uma Piadoca djakfjakfjakfjakfjakfajkfjakfjakfjakfjakfjakfajkfajfka";

        itens.add(newPiada);

        piadasCategoriaAdapter.notifyItemInserted(itens.size()-1);
        RecyclerView rvPiadasCategoria = findViewById(R.id.rvPiadasCategoria);
        rvPiadasCategoria.setLayoutManager(new LinearLayoutManager(this));
        rvPiadasCategoria.setAdapter(piadasCategoriaAdapter);
    }
}