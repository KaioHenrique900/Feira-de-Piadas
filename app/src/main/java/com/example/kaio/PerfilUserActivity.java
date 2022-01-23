package com.example.kaio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
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

    String uid = "2";

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

        Spinner sUserOptions = findViewById(R.id.spinnerUserOptions);
        sUserOptions.setSelection(-1);
        sUserOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1){
                    Config.setLogin(PerfilUserActivity.this, "");
                    Config.setPassword(PerfilUserActivity.this, "");
                    Intent i = new Intent(PerfilUserActivity.this, SignInActivity.class);
                    startActivity(i);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

                        final String id_user = jsonObject.getString("id_usuario");
                        final String nome_user = jsonObject.getString("nome");
                        final String email_user = jsonObject.getString("email");
                        final String senha_user = jsonObject.getString("senha");
                        final String data_nasc_user = jsonObject.getString("data_nasc");

                        User user = new User(id_user, nome_user, email_user, senha_user, data_nasc_user);

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

        PerfilUserViewModel vm = new ViewModelProvider(PerfilUserActivity.this).get(PerfilUserViewModel.class);

        vm.setUid(uid);

        List<MyItemPiada> itens = vm.getItens();

        RecyclerView rvPerfilUser = findViewById(R.id.rvPerfilUser);
        rvPerfilUser.setLayoutManager(new LinearLayoutManager(this));

        LiveData<List<MyItemPiada>> piadas = vm.getPiadas();
        piadas.observe(PerfilUserActivity.this, new Observer<List<MyItemPiada>>() {
            @Override
            public void onChanged(List<MyItemPiada> piadas) {
                PerfilUserAdapter perfilUserAdapter = new PerfilUserAdapter(PerfilUserActivity.this, piadas);
                rvPerfilUser.setAdapter(perfilUserAdapter);
            }
        });
    }
}