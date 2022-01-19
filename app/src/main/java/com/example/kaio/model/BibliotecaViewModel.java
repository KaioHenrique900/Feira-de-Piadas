package com.example.kaio.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.kaio.Config;
import com.example.kaio.HttpRequest;
import com.example.kaio.MyItemBiblioteca;
import com.example.kaio.MyItemPiada;
import com.example.kaio.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BibliotecaViewModel extends ViewModel {
    MutableLiveData<List<MyItemBiblioteca>> categorias;

    public LiveData<List<MyItemBiblioteca>> getCategorias() {
        if (categorias == null){  //chamando uma instancia
            categorias = new MutableLiveData<List<MyItemBiblioteca>>();
            loadPiadas();
        }
        return categorias;
    }

    public void refreshPiadas(){
        loadPiadas();
    }

    void loadPiadas(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {

                List<MyItemBiblioteca> categoriasList = new ArrayList<>();
                HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL_BASE + "get_all_categorias.php", "GET", "UTF-8");  //url deve ser alterada

                try {
                    InputStream is = httpRequest.execute();   //IntputStrem é um fluxo de dados"
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    Log.d("HTTP_REQUEST_RESULT", result);

                    JSONObject jsonObject = new JSONObject(result);
                    int success = jsonObject.getInt("success");
                    if(success == 1){
                        JSONArray jsonArray = jsonObject.getJSONArray("categorias");
                        for (int i = 0; i<jsonArray.length(); i++){
                            JSONObject jCategoria = jsonArray.getJSONObject(i);

                            String id = jCategoria.getString("id_categoria");
                            String descricao = jCategoria.getString("descricao");

                            MyItemBiblioteca biblioteca = new MyItemBiblioteca();
                            biblioteca.idCategoria = id;
                            biblioteca.nomeCategoria = descricao;

                            categoriasList.add(biblioteca);  //min:35
                        }
                        categorias.postValue(categoriasList);  //Para uma nova thread
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
