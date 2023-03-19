package com.example.proyecto1;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.proyecto1.databinding.FragmentRecetasBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecetasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecetasFragment extends Fragment {

    private FragmentRecetasBinding binding;



    public RecetasFragment() {
        // Required empty public constructor
    }


    public static RecetasFragment newInstance(String param1, String param2) {
        RecetasFragment fragment = new RecetasFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRecetasBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_recetas
                , container, false);

        Button crear=(Button) view.findViewById(R.id.cr_crear);
        Button recetas=(Button) view.findViewById(R.id.cr_ver);

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CrearRecetas.class);
                startActivity(intent);
            }
        });

        recetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MostrarRecetas.class);
                startActivity(intent);

            }
        });
        return view;

    }
    @Override
    public void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
        outstate.putInt("currentFragment",2);
    }
}