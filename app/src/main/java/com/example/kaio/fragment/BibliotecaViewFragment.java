package com.example.kaio.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.kaio.PiadasCategoriaActivity;
import com.example.kaio.PublicarPiadaActivity;
import com.example.kaio.R;
import com.example.kaio.model.PiadasCategoriaViewModel;

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
        View view =  inflater.inflate(R.layout.fragment_biblioteca_view, container, false);

        ImageView imgCat6 = view.findViewById(R.id.imgCat6);
        imgCat6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PiadasCategoriaActivity.class);
                startActivity(i);
            }
        });
        return view;
    }
}