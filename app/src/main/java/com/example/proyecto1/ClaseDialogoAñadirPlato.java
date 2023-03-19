package com.example.proyecto1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

public class ClaseDialogoAñadirPlato extends DialogFragment {
    private static Integer pdia;
    private static Integer pmes;
    private static Integer paño;

    public ClaseDialogoAñadirPlato(){

    }

    //Crea un dialogo en el que te da a elegir entre añadir plato o ver plato y manda la fecha seleccionada mediante intent para saber la fecha
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Selecione una accion");
        final CharSequence[] colores = {"Añadir un plato", "Ver platos"};

        builder.setSingleChoiceItems(colores,-1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                switch (i) {
                    case 0:
                        Intent intent = new Intent(getActivity(), CrearPlatos.class);
                        intent.putExtra("dia",pdia);
                        intent.putExtra("mes",pmes);
                        intent.putExtra("año",paño);

                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent2 = new Intent(getActivity(), MostrarPlatos.class);
                        intent2.putExtra("dia",pdia);
                        intent2.putExtra("mes",pmes);
                        intent2.putExtra("año",paño);
                        startActivity(intent2);
                        break;
                }

            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        return builder.create();
    }
    public static void setMes(Integer pMes){
        pmes=pMes;
    }
    public static void setAño(Integer pAño){
        paño=pAño;
    }
    public static void setDia(Integer pDia){
        pdia=pDia;
    }
}
