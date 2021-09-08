package com.example.kaio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PerfilActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        bottomNavigationView = findViewById(R.id.btNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.HomeViewOp:
                        /*HomeViewFragment homeViewFragment = HomeViewFragment.newInstance();
                        setFragment(homeViewFragment);*/
                        break;
                    case R.id.bibliotecaViewOp:
                        /*BibliotecaViewFragment bibliotecaViewFragment = BibliotecaViewFragment.newInstance();
                        setFragment(bibliotecaViewFragment);*/
                        break;
                    case R.id.topPiadasViewOp:
                        /*TopPiadasViewFragment topPiadasViewFragment = TopPiadasViewFragment.newInstance();
                        setFragment(topPiadasViewFragment);*/
                        break;
                }
                return true;
            }
        });
    }
}