package com.example.kaio.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kaio.Config;
import com.example.kaio.HomeActivity;
import com.example.kaio.HomeAdapter;
import com.example.kaio.HttpRequest;
import com.example.kaio.MyItemPiada;
import com.example.kaio.R;
import com.example.kaio.TopPiadasAdapter;
import com.example.kaio.model.HomeViewModel;
import com.example.kaio.model.TopPiadasViewModel;
import com.example.kaio.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

        RecyclerView rvTopPiadas = getActivity().findViewById(R.id.rvTopPiadas);
        rvTopPiadas.setHasFixedSize(true);
        rvTopPiadas.setLayoutManager(new LinearLayoutManager(getActivity()));

        TopPiadasViewModel topPiadasViewModel = new ViewModelProvider(((HomeActivity)getActivity())).get(TopPiadasViewModel.class);
        LiveData<List<MyItemPiada>> piadas = topPiadasViewModel.getPiadas();
        topPiadasViewModel.setLogin(Config.getLogin(getContext()));
        piadas.observe(getActivity(), new Observer<List<MyItemPiada>>() {
            @Override
            public void onChanged(List<MyItemPiada> piadas) {
                TopPiadasAdapter topPiadasAdapter = new TopPiadasAdapter(getContext(), piadas);
                rvTopPiadas.setAdapter(topPiadasAdapter);
            }
        });




    }
}