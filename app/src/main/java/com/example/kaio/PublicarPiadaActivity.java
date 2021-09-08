package com.example.kaio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class PublicarPiadaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicar_piada);

        Button btnPublicar = findViewById(R.id.btnPublicar2);
        btnPublicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PublicarPiadaActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });

        Button btnCancelar = findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PublicarPiadaActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
    }
}