package com.example.kaio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kaio.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        EditText etDate = findViewById(R.id.etDateUp);
        etDate.addTextChangedListener(Util.mask(etDate, "##/##/####"));

        Button btnCadastrar = findViewById(R.id.btnCadastrarUp);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etUserUp =  findViewById(R.id.etUserUp);
                final String userUp = etUserUp.getText().toString();
                if(userUp.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Campo de Usuário não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etEmailUp =  findViewById(R.id.etEmailUp);
                final String emailUp = etEmailUp.getText().toString();
                if(emailUp.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Campo de Email não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etSenhaUp =  findViewById(R.id.etSenhaUp);
                final String senhaUp = etSenhaUp.getText().toString();
                if(senhaUp.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Campo de Senha não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etCSenha =  findViewById(R.id.etCSenhaUp);
                String cSenha = etCSenha.getText().toString();
                if(cSenha.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Campo de Checagem de Senha não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etDateUp =  findViewById(R.id.etDateUp);
                final String dateUp = etDateUp.getText().toString();
                if(dateUp.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Campo de Data de Nascimento não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }

                if(!senhaUp.equals(cSenha)) {
                    Toast.makeText(SignUpActivity.this, "Senha não confere", Toast.LENGTH_LONG).show();
                    return;
                }

                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL_BASE + "register.php", "POST", "UTF-8");
                        httpRequest.addParam("userUp", userUp);
                        httpRequest.addParam("senhaUp", senhaUp);
                        httpRequest.addParam("emailUp", emailUp);
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
                                        Toast.makeText(SignUpActivity.this, "Novo usuario cadastrado com sucesso", Toast.LENGTH_LONG).show();
                                        Config.setLogin(SignUpActivity.this, emailUp);
                                        Config.setPassword(SignUpActivity.this, senhaUp);
                                        Intent i = new Intent(SignUpActivity.this, HomeActivity.class);
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
                                        Toast.makeText(SignUpActivity.this, error, Toast.LENGTH_LONG).show();
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
    }
}