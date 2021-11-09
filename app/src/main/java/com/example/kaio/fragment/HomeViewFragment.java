package com.example.kaio.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kaio.PerfilActivity;
import com.example.kaio.PublicarPiadaActivity;
import com.example.kaio.R;

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

        TextView tvUserName = view.findViewById(R.id.tvUserName);
        tvUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PerfilActivity.class);
                startActivity(i);
            }
        });

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

        /*HomeActivityViewModel homeActivityViewModel = new ViewModelProvider(this).get(HomeActivityViewModel.class);
        HomeAdapter homeAdapter = new HomeAdapter(getContext());
        RecyclerView rv = getView().findViewById(R.id.rvHome);
        rv.setAdapter(homeAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));*/


    }
}