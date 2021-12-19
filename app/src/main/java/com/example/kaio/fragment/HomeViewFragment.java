package com.example.kaio.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kaio.Config;
import com.example.kaio.HomeActivity;
import com.example.kaio.HomeAdapter;
import com.example.kaio.HttpRequest;
import com.example.kaio.MyItemPiada;
import com.example.kaio.PerfilActivity;
import com.example.kaio.PerfilUserAdapter;
import com.example.kaio.PublicarPiadaActivity;
import com.example.kaio.R;
import com.example.kaio.model.HomeViewModel;
import com.example.kaio.model.PerfilUserViewModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class  HomeViewFragment extends Fragment {

    public HomeViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeViewFragment newInstance() {
        HomeViewFragment fragment = new HomeViewFragment();
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
        View view = inflater.inflate(R.layout.fragment_home_view, container, false);
        /*
        HomeViewModel vm = new ViewModelProvider(this).get(HomeViewModel.class);
        List<MyItemPiada> itens = vm.getItens();

        HomeAdapter homeAdapter = new HomeAdapter(getActivity(), itens);

        RecyclerView rvHome = view.findViewById(R.id.rvHome);
        rvHome.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvHome.setLayoutManager(layoutManager);

        rvHome.setAdapter(homeAdapter);

        TextView tvUserName = view.findViewById(R.id.tvUserNameHome);
        tvUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PerfilActivity.class);
                startActivity(i);
            }
        });*/



        ImageButton btnPublicar = view.findViewById(R.id.btnPublicar);
        btnPublicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PublicarPiadaActivity.class);
                startActivity(i);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        HomeViewModel vm = new ViewModelProvider(this).get(HomeViewModel.class);
        List<MyItemPiada> itens = vm.getItens();

        HomeAdapter homeAdapter = new HomeAdapter(getActivity(), itens);

        MyItemPiada newPiada = new MyItemPiada();
        newPiada.user = "kaio@gmail.com";
        newPiada.titulo = "Abre o olho";
        newPiada.piada = "O homem leva o filho ao pediatra:\\n\n" +
                "- Sr. Dr., o meu filho completou seis meses e até hoje não abriu os olhos!\\n\n" +
                "- Abra os olhos você, esse garoto é filho de japonês!\n";

        itens.add(newPiada);

        newPiada = new MyItemPiada();
        newPiada.user = "KaioAdmin";
        newPiada.titulo = "Joãozinho desligado";
        newPiada.piada = "Joãzinho voltando da escola chateado,\n" +
                "        chega em casa para desabafar com sua mãe:\\n\n" +
                "        - Mãe, as outras crianças na escola me chamam de desligado.\\n\n" +
                "        A mãe então responde:\\n\n" +
                "        - Garoto, você mora na casa do lado.";

        itens.add(newPiada);

        newPiada = new MyItemPiada();
        newPiada.user = "Kaio";
        newPiada.titulo = "Piada Titulo";
        newPiada.piada = "Uma Piadoca fajfkajfkajfkajfkafjakfjafkjakajfkajakfjakafjakajfkajakj";

        itens.add(newPiada);

        homeAdapter.notifyItemInserted(itens.size()-1);
        RecyclerView rvHome = getActivity().findViewById(R.id.rvHome);
        rvHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvHome.setAdapter(homeAdapter);
    }
}