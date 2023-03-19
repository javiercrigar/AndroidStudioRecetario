package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import java.util.Locale;

public class CrearRecetas extends AppCompatActivity {
   // private Button volver;
    //private Button crear;
    //private Integer color=Color.WHITE;
    //private String idioma="eu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState!= null){
            //color= savedInstanceState.getInt("color");
            //idioma= savedInstanceState.getString("idioma");
        }
        setContentView(R.layout.activity_crear_recetas);

        EditText titulo=(EditText) findViewById(R.id.cre_titulo);
        EditText pasos=(EditText) findViewById(R.id.cre_pasos);
        Button next=(Button) findViewById(R.id.cre_bot_crear);
        Button volver=(Button) findViewById(R.id.cre_bot_volver);

        //Boton para crear la receta
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miDB DB =new miDB(getApplicationContext(),"Usuar.db",null,1);
                SQLiteDatabase db = DB.getWritableDatabase();

                if(titulo.getText().toString().isEmpty()==false &&pasos.getText().toString().isEmpty()==false){
                    DB.crearnuevareceta(titulo.getText().toString(),pasos.getText().toString());
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(CrearRecetas.this, "Un campo vacio", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //Boton para volver a Home
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //savedInstanceState.putString("idioma", Locale.getDefault().getLanguage());
        //savedInstanceState.putInt("color",color);
        //savedInstanceState.putString("idioma",idioma);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        //color=savedInstanceState.getInt("color");
        //idioma=savedInstanceState.getString("idioma");
    }
}