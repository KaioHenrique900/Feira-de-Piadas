package com.example.kaio.model;

import androidx.lifecycle.ViewModel;

import com.example.kaio.MyItemBiblioteca;
import com.example.kaio.MyItemPiada;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaViewModel extends ViewModel {
    List<MyItemBiblioteca> itens = new ArrayList<>();

    public List<MyItemBiblioteca> getItens() {
        return itens;
    }
}
