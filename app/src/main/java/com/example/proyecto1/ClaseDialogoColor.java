package com.example.proyecto1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class ClaseDialogoColor extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Selecione color de fondo");
        final CharSequence[] colores = {"Magenta", "Blanco", "Cyan"};

        builder.setSingleChoiceItems(colores,-1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                switch (i) {
                    case 0:
                       getActivity().getWindow().getDecorView().setBackgroundColor(Color.MAGENTA);
                       Home x= new Home();
                       //x.setColor(Color.MAGENTA);
                        break;
                    case 1:
                        getActivity().getWindow().getDecorView().setBackgroundColor(Color.WHITE);

                        break;
                    case 2:
                        getActivity().getWindow().getDecorView().setBackgroundColor(Color.CYAN);
                        break;
                }
                //    startActivity(intent);
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        return builder.create();
    }

}
