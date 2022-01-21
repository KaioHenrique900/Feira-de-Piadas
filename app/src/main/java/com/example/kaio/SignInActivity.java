package com.example.kaio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Bundle;
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

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Button btnEntrar = findViewById(R.id.btnEntrar);
        btnEntrar.setEnabled(true);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setEnabled(false);

                EditText etEmail = findViewById(R.id.etEmail);
                final String email = etEmail.getText().toString();

                EditText etPassword = findViewById(R.id.etPassword);
                final String password = etPassword.getText().toString();

                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL_BASE + "login.php", "POST", "UTF-8");
                        httpRequest.setBasicAuth(email, password);

                        try{
                            InputStream is = httpRequest.execute();
                            String result = Util.inputStream2String(is, "UTF-8");
                            httpRequest.finish();

                            JSONObject jsonObject = new JSONObject(result);
                            final int success = jsonObject.getInt("success");
                            if(success == 1){
                                String uid = null;
                                try {
                                    uid = jsonObject.getString("uid");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                String finalUid = uid;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        Config.setLogin(SignInActivity.this, email);
                                        Config.setPassword(SignInActivity.this, password);
                                        Config.setUid(SignInActivity.this, finalUid);
                                        Toast.makeText(SignInActivity.this, "Login realizado", Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(SignInActivity.this, HomeActivity.class);
                                        startActivity(i);
                                    }
                                });
                            }
                            else{
                                final String error = jsonObject.getString("error");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(SignInActivity.this, error, Toast.LENGTH_LONG).show();
                                        v.setEnabled(true);
                                    }
                                });
                            }
                        } catch(IOException | JSONException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        Button btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });

    }
}