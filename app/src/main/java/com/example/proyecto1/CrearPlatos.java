package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CrearPlatos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_platos);

        EditText titulo=(EditText) findViewById(R.id.cpt_titulo);
        Button next=(Button) findViewById(R.id.cpt_bot_crear);
        Button volver=(Button) findViewById(R.id.cpt_bot_volver);
        Intent x=getIntent();
        Integer dia=x.getIntExtra("dia",1);
        Integer mes=x.getIntExtra("mes",1);
        Integer año=x.getIntExtra("año",1);


        //Boton para crear la receta
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miDB DB =new miDB(getApplicationContext(),"Usuar.db",null,1);
                SQLiteDatabase db = DB.getWritableDatabase();

                if(titulo.getText().toString().isEmpty()==false ){
                    DB.crearnuevoplato(dia,mes,año,titulo.getText().toString());
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(CrearPlatos.this, "Un campo vacio", Toast.LENGTH_SHORT).show();
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
}