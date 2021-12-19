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
import android.widget.TextView;
import android.widget.Toast;

import com.example.kaio.model.HomeViewModel;
import com.example.kaio.model.PerfilUserViewModel;
import com.example.kaio.model.PerfilViewModel;
import com.example.kaio.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

        ImageView imExit = findViewById(R.id.imExit);
        imExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Config.setLogin(PerfilUserActivity.this, "");
                Config.setPassword(PerfilUserActivity.this, "");
                Intent i = new Intent(PerfilUserActivity.this, SignInActivity.class);
                startActivity(i);
            }
        });

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL_BASE + "get_data.php", "POST", "UTF-8");
                httpRequest.addParam("email", Config.getLogin(PerfilUserActivity.this));

                try {
                    InputStream is = httpRequest.execute();
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    JSONObject jsonObject = new JSONObject(result);
                    final int success = jsonObject.getInt("success");
                    if(success == 1) {
                        final String webData = jsonObject.getString("data");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView tvWebData = findViewById(R.id.tvUserPerfil);
                                tvWebData.setText(webData);
                            }
                        });

                    }
                    else {
                        final String error = jsonObject.getString("error");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PerfilUserActivity.this, error, Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
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