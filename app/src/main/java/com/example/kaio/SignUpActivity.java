package com.example.kaio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kaio.util.Util;

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
                Intent i = new Intent(SignUpActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
    }
}