package com.example.kaio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kaio.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PublicarPiadaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicar_piada);

        Button btnPublicar = findViewById(R.id.btnPublicar2);
        btnPublicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etTituloPiada =  findViewById(R.id.etTituloPiada);
                final String tituloPiada = etTituloPiada.getText().toString();
                if(tituloPiada.isEmpty()) {
                    Toast.makeText(PublicarPiadaActivity.this, "Campo de Titulo não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }

                Spinner spinnerCategorias =  findViewById(R.id.spinnerCategorias);
                final String categoria = spinnerCategorias.getSelectedItem().toString();
                if(categoria.isEmpty()) {
                    Toast.makeText(PublicarPiadaActivity.this, "Uma categoria deve ser selecionada", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etConteudoPiada =  findViewById(R.id.etConteudoPiada);
                final String conteudoPiada = etConteudoPiada.getText().toString();
                if(conteudoPiada.isEmpty()) {
                    Toast.makeText(PublicarPiadaActivity.this, "Campo Escreva sua Piada não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }

                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL_BASE + "insert_piada.php", "POST", "UTF-8");
                        httpRequest.addParam("tituloPiada", tituloPiada);
                        httpRequest.addParam("categoria", categoria);
                        httpRequest.addParam("conteudoPiada", conteudoPiada);
                        //httpRequest.addParam("newDateN", dateUp);

                        try {
                            InputStream is = httpRequest.execute();
                            String result = Util.inputStream2String(is, "UTF-8");
                            httpRequest.finish();
                            JSONObject jsonObject = new JSONObject(result);
                            final int success = jsonObject.getInt("success");
                            if(success == 1) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(PublicarPiadaActivity.this, "Piada publicada com sucesso", Toast.LENGTH_LONG).show();

                                        Intent i = new Intent(PublicarPiadaActivity.this, HomeActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                });
                            }
                            else {
                                final String error = jsonObject.getString("error");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(PublicarPiadaActivity.this, error, Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
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