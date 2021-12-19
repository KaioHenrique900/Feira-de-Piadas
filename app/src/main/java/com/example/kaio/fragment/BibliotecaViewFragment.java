package com.example.kaio.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.kaio.BibliotecaAdapter;
import com.example.kaio.HomeAdapter;
import com.example.kaio.MyItemBiblioteca;
import com.example.kaio.MyItemPiada;
import com.example.kaio.PiadasCategoriaActivity;
import com.example.kaio.PublicarPiadaActivity;
import com.example.kaio.R;
import com.example.kaio.model.BibliotecaViewModel;
import com.example.kaio.model.HomeViewModel;
import com.example.kaio.model.PiadasCategoriaViewModel;
import com.example.kaio.util.Util;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BibliotecaViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BibliotecaViewFragment extends Fragment {


    public BibliotecaViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BibliotecaViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BibliotecaViewFragment newInstance() {
        BibliotecaViewFragment fragment = new BibliotecaViewFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_biblioteca_view_1, container, false);

        /*ImageView imgCat1 = view.findViewById(R.id.imgCat1);
        imgCat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PiadasCategoriaActivity.class);
                startActivity(i);
            }
        });*/
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BibliotecaViewModel vm = new ViewModelProvider(this).get(BibliotecaViewModel.class);
        List<MyItemBiblioteca> itens = vm.getItens();
        BibliotecaAdapter bibliotecaAdapter = new BibliotecaAdapter(getActivity(), itens);

        MyItemBiblioteca newCategoria = new MyItemBiblioteca();

        newCategoria.nomeCategoria = getResources().getString(R.string.categoria1);
        itens.add(newCategoria);

        newCategoria = new MyItemBiblioteca();

        newCategoria.nomeCategoria = getResources().getString(R.string.categoria2);
        itens.add(newCategoria);

        newCategoria = new MyItemBiblioteca();

        newCategoria.nomeCategoria = getResources().getString(R.string.categoria3);
        itens.add(newCategoria);

        newCategoria = new MyItemBiblioteca();

        newCategoria.nomeCategoria = getResources().getString(R.string.categoria4);
        itens.add(newCategoria);

        newCategoria = new MyItemBiblioteca();

        newCategoria.nomeCategoria = getResources().getString(R.string.categoria5);
        itens.add(newCategoria);

        newCategoria = new MyItemBiblioteca();

        newCategoria.nomeCategoria = getResources().getString(R.string.categoria6);
        itens.add(newCategoria);

        newCategoria = new MyItemBiblioteca();

        newCategoria.nomeCategoria = getResources().getString(R.string.categoria7);
        itens.add(newCategoria);

        newCategoria = new MyItemBiblioteca();

        newCategoria.nomeCategoria = getResources().getString(R.string.categoria8);
        itens.add(newCategoria);

        newCategoria = new MyItemBiblioteca();

        newCategoria.nomeCategoria = getResources().getString(R.string.categoria9);
        itens.add(newCategoria);

        newCategoria = new MyItemBiblioteca();

        newCategoria.nomeCategoria = getResources().getString(R.string.categoria10);
        itens.add(newCategoria);

        newCategoria = new MyItemBiblioteca();

        newCategoria.nomeCategoria = getResources().getString(R.string.categoria11);
        itens.add(newCategoria);

        newCategoria = new MyItemBiblioteca();

        newCategoria.nomeCategoria = getResources().getString(R.string.categoria12);
        itens.add(newCategoria);

        newCategoria = new MyItemBiblioteca();

        newCategoria.nomeCategoria = getResources().getString(R.string.categoria13);
        itens.add(newCategoria);

        newCategoria = new MyItemBiblioteca();

        newCategoria.nomeCategoria = getResources().getString(R.string.categoria14);
        itens.add(newCategoria);

        newCategoria = new MyItemBiblioteca();

        newCategoria.nomeCategoria = getResources().getString(R.string.categoria15);
        itens.add(newCategoria);

        newCategoria = new MyItemBiblioteca();

        newCategoria.nomeCategoria = getResources().getString(R.string.categoria16);
        itens.add(newCategoria);

        newCategoria = new MyItemBiblioteca();

        newCategoria.nomeCategoria = getResources().getString(R.string.categoria17);
        itens.add(newCategoria);

        newCategoria = new MyItemBiblioteca();

        newCategoria.nomeCategoria = getResources().getString(R.string.categoria18);
        itens.add(newCategoria);

        newCategoria = new MyItemBiblioteca();

        newCategoria.nomeCategoria = getResources().getString(R.string.categoria19);
        itens.add(newCategoria);

        newCategoria = new MyItemBiblioteca();

        newCategoria.nomeCategoria = getResources().getString(R.string.categoria20);
        itens.add(newCategoria);

        float w = getResources().getDimension(R.dimen.b_icon_width);
        int nColumns = Util.calculateNoOfColumns(getContext(), w);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), nColumns);

        RecyclerView rvBiblioteca = getActivity().findViewById(R.id.rvBiblioteca);
        rvBiblioteca.setAdapter(bibliotecaAdapter);
        rvBiblioteca.setLayoutManager(gridLayoutManager);
    }
}