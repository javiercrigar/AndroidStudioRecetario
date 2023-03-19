package com.example.proyecto1;

import static androidx.core.app.ActivityCompat.recreate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.VolumeShaper;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.fragment.app.DialogFragment;

import java.util.Locale;

public class ClaseDialogoIdioma extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Selecione el idioma");
        final CharSequence[] niveles = {"Español", "Euskera", "Ingles"};

        builder.setSingleChoiceItems(niveles,-1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Intent intent = new Intent(getActivity(), Home.class);
                //startActivity(intent);
                // Falta hacer el codigo de cambiar idioma
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                switch (i) {
                    case 0:
                        cambiarIdioma("es");

                        editor.putString("idioma", "es");
                        editor.apply();
                        break;
                    case 1:
                        cambiarIdioma("eu");
                        editor.putString("idioma", "eu");
                        editor.apply();
                        break;
                    case 2:
                        cambiarIdioma("en");
                        editor.putString("idioma", "en");
                        editor.apply();
                        break;
                }}
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        return builder.create();
    }

    private void cambiarIdioma(String lenguaje) {
        Locale locale = new Locale(lenguaje);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getActivity().getResources().updateConfiguration(config,
                getActivity().getResources().getDisplayMetrics());
        recreate(this.getActivity());
    }

}