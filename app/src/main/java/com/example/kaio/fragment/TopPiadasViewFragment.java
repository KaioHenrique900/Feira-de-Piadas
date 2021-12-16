package com.example.kaio.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kaio.HomeAdapter;
import com.example.kaio.MyItemPiada;
import com.example.kaio.R;
import com.example.kaio.TopPiadasAdapter;
import com.example.kaio.model.HomeViewModel;
import com.example.kaio.model.TopPiadasViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TopPiadasViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopPiadasViewFragment extends Fragment {

    public TopPiadasViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TopPiadasViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopPiadasViewFragment newInstance() {
        TopPiadasViewFragment fragment = new TopPiadasViewFragment();
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
        return inflater.inflate(R.layout.fragment_top_piadas_view, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TopPiadasViewModel vm = new ViewModelProvider(this).get(TopPiadasViewModel.class);
        List<MyItemPiada> itens = vm.getItens();

        TopPiadasAdapter topPiadasAdapter = new TopPiadasAdapter(getActivity(), itens);

        MyItemPiada newPiada = new MyItemPiada();
        newPiada.user = "Kaio";
        newPiada.position = "1.";
        newPiada.titulo = "Piada Titulo";
        newPiada.piada = "Uma Piadoca djakfjakfjakfjakfjakfajkfjakfjakfjakfjakfjakfajkfajfka";

        itens.add(newPiada);

        newPiada = new MyItemPiada();
        newPiada.user = "Kaio";
        newPiada.position = "2.";
        newPiada.titulo = "Piada Titulo";
        newPiada.piada = "Uma Piadoca fajfkajfkajfkajfkafjakfjafkjakajfkajakfjakafjakajfkajakj";

        itens.add(newPiada);

        newPiada = new MyItemPiada();
        newPiada.user = "Kaio";
        newPiada.position = "3.";
        newPiada.titulo = "Piada Titulo";
        newPiada.piada = "Uma Piadoca fajfkajfkajfkajfkafjakfjafkjakajfkajakfjakafjakajfkajakj";

        itens.add(newPiada);

        topPiadasAdapter.notifyItemInserted(itens.size()-1);
        RecyclerView rvTopPiadas = getActivity().findViewById(R.id.rvTopPiadas);
        rvTopPiadas.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvTopPiadas.setAdapter(topPiadasAdapter);


    }
}