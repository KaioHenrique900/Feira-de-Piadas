package com.example.kaio.model;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.kaio.Config;
import com.example.kaio.HomeActivity;
import com.example.kaio.HttpRequest;
import com.example.kaio.MyItemPiada;
import com.example.kaio.PerfilUserActivity;
import com.example.kaio.User;
import com.example.kaio.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PerfilUserViewModel  extends ViewModel {
    List<MyItemPiada> itens = new ArrayList<>();

    String login;

    MutableLiveData<User> user;

    MutableLiveData<List<MyItemPiada>> piadas;  //mutable lvedata é um livedata que se pode alterar


    public void setLogin(String login) {
        this.login = login;
    }

    public void setUser(MutableLiveData<User> user) {
        this.user = user;


    }

    public LiveData<List<MyItemPiada>> getPiadas(){
        if (piadas == null){
            piadas = new MutableLiveData<List<MyItemPiada>>();   //chamando uma instancia
            loadPiadas();
        }

        return piadas;
    }

    public List<MyItemPiada> getItens() {
        return itens;
    }

    public void refreshPiadas(){
        loadPiadas();
    }

    void loadPiadas(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {

            @Override
            public void run() {
                List<MyItemPiada> piadasList = new ArrayList<>();
                HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL_BASE + "get_user_piadas.php", "POST", "UTF-8");  //url deve ser alterada
                httpRequest.addParam("email", login);

                try {
                    InputStream is = httpRequest.execute();   //IntputStrem é um fluxo de dados"
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    Log.d("HTTP_REQUEST_RESULT", result);

                    JSONObject jsonObject = new JSONObject(result);
                    int success = jsonObject.getInt("success");
                    if(success == 1){
                        JSONArray jsonArray = jsonObject.getJSONArray("piadas");
                        for (int i = jsonArray.length()-1; i>=0; i--){
                            JSONObject jPiada = jsonArray.getJSONObject(i);

                            String id = jPiada.getString("id_piada");
                            String titulo = jPiada.getString("titulo");
                            String descricao = jPiada.getString("descricao");
                            String data_publicacao = jPiada.getString("data_publicacao");
                            String nome_usuario = jPiada.getString("nome_usuario");
                            int liked = jPiada.getInt("curtida");
                            String likes = jPiada.getString("likes");

                            MyItemPiada piada = new MyItemPiada();
                            piada.piada = descricao;
                            piada.pid = id;
                            piada.titulo = titulo;
                            piada.data_publicacao = data_publicacao;
                            piada.user = nome_usuario;
                            piada.liked = liked;
                            piada.likes = likes;

                            piadasList.add(piada);  //min:35
                        }
                        piadas.postValue(piadasList);  //Para uma nova thread
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /*static public class PerfilUserViewModelFactory implements ViewModelProvider.Factory{  //ensina como construir o viewProductViewModel

        String uid;

        public PerfilUserViewModelFactory(String uid) {
            this.uid = uid;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new PerfilUserViewModel(uid);
        }
    }*/
}
