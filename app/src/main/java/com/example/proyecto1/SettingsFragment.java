package com.example.proyecto1;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.proyecto1.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {
    private FragmentSettingsBinding binding;
    private Button bor_platos;
    private Button bor_recetas;
    private Button bor_usuarios;

    public SettingsFragment() {
        // Required empty public constructor
    }

      public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        //boton para borra todos los platos
        bor_platos=(Button) view.findViewById(R.id.aju_bot_borrar_platos);
        bor_platos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miDB DB =new miDB(getContext(),"Usuar.db",null,1);
                SQLiteDatabase db = DB.getWritableDatabase();
                db.execSQL("DELETE FROM t_platos");
            }
        });

        //Boton para borrar todas las recetas
        bor_recetas=view.findViewById(R.id.aju_bot_borrar_recetas);
        bor_recetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miDB DB =new miDB(getContext(),"Usuar.db",null,1);
                SQLiteDatabase db = DB.getWritableDatabase();
                db.execSQL("DELETE FROM t_recetas");
            }
        });

        //Boton para borrar todos los usuarios
        bor_usuarios=view.findViewById(R.id.aju_bot_borrar_usuarios);
        bor_usuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miDB DB =new miDB(getContext(),"Usuar.db",null,1);
                SQLiteDatabase db = DB.getWritableDatabase();
                db.execSQL("DELETE FROM t_usuarios");

                Intent intent= new Intent(getContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}