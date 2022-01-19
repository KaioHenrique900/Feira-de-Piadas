package com.example.kaio.model;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.kaio.Config;
import com.example.kaio.HttpRequest;
import com.example.kaio.MyItemPiada;
import com.example.kaio.SignInActivity;
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

public class PiadasCategoriaViewModel extends ViewModel {
    List<MyItemPiada> itens = new ArrayList<>();
    String categoria;

    MutableLiveData<List<MyItemPiada>> piadas;  //mutable lvedata é um livedata que se pode alterar

    public LiveData<List<MyItemPiada>> getPiadas(){
        if (piadas == null){
            piadas = new MutableLiveData<List<MyItemPiada>>();   //chamando uma instancia
            loadPiadas();
        }

        return piadas;
    }

    public void setCategoria(String categoria){
        this.categoria = categoria;
    }

    public List<MyItemPiada> getItens() {
        return itens;
    }

    public void refreshPiadas(){
        loadPiadas();
    }

    void loadPiadas(){
        System.out.println(categoria);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {

                List<MyItemPiada> piadasList = new ArrayList<>();
                HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL_BASE + "get_piadas_categoria.php", "POST", "UTF-8");  //url deve ser alterada
                httpRequest.addParam("categoria", categoria);

                try {
                    InputStream is = httpRequest.execute();   //IntputStrem é um fluxo de dados"
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    Log.d("HTTP_REQUEST_RESULT", result);

                    JSONObject jsonObject = new JSONObject(result);
                    int success = jsonObject.getInt("success");
                    if(success == 1){
                        JSONArray jsonArray = jsonObject.getJSONArray("piadas");
                        if (jsonArray.length() > 0) {
                            for (int i = jsonArray.length() - 1; i >= 0; i--) {
                                JSONObject jPiada = jsonArray.getJSONObject(i);

                                String id = jPiada.getString("id_piada");
                                String titulo = jPiada.getString("titulo");
                                String descricao = jPiada.getString("descricao");
                                String data_publicacao = jPiada.getString("data_publicacao");
                                String nome_usuario = jPiada.getString("nome_usuario");

                                MyItemPiada piada = new MyItemPiada();
                                piada.piada = descricao;
                                piada.pid = id;
                                piada.titulo = titulo;
                                piada.data_publicacao = data_publicacao;
                                piada.user = nome_usuario;

                                piadasList.add(piada);  //min:35
                            }
                            piadas.postValue(piadasList);  //Para uma nova thread
                        }
                        else{
                            //enviar mensagem dizendo que não há piadas
                        }
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
