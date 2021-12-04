package com.example.kaio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Config.getLogin(MainActivity.this).isEmpty()){
            Intent i = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(i);
            finish();
        }
        else{
            Intent i = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(i);
            finish();
        }
    }
}