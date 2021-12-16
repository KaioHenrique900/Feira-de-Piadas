package com.example.kaio.model;

import androidx.lifecycle.ViewModel;

import com.example.kaio.MyItemPiada;

import java.util.ArrayList;
import java.util.List;

public class PerfilViewModel extends ViewModel {
    List<MyItemPiada> itens = new ArrayList<>();

    public List<MyItemPiada> getItens() {
        return itens;
    }
}
