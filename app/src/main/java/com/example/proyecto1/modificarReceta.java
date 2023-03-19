package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class modificarReceta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_receta);


        Button borrar=findViewById(R.id.modi_bot_borrar);
        Button volver=findViewById(R.id.modi_bot_voler);
        Button guardar=findViewById(R.id.modi_bot_guardar);

        EditText titulo= findViewById(R.id.modi_nombre);
        EditText pasos =findViewById(R.id.modi_texto);

        Intent intent=getIntent();


        titulo.setText(intent.getStringExtra("titulo"));
        pasos.setText(intent.getStringExtra("pasos"));
        String titulo1=titulo.getText().toString();

        //Vuelve a home
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(modificarReceta.this,MostrarRecetas.class);
                startActivity(intent);
            }
        });

        //Elimina la receta
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miDB DB =new miDB(getApplicationContext(),"Usuar.db",null,1);
                SQLiteDatabase db = DB.getWritableDatabase();

                db.delete("t_recetas","titulo=?",new String[]{titulo.getText().toString()});
                db.close();

                Toast.makeText(modificarReceta.this, "Has borrado la receta", Toast.LENGTH_SHORT).show();

                Intent intent= new Intent(modificarReceta.this,MostrarRecetas.class);
                startActivity(intent);
            }
        });

        //Si el nombre y los pasos no son vacios los modifica
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                miDB DB =new miDB(getApplicationContext(),"Usuar.db",null,1);
                SQLiteDatabase db = DB.getWritableDatabase();
                if(titulo.getText().toString().isEmpty()==false && pasos.getText().toString().isEmpty()==false){
                    db.delete("t_recetas","titulo=?",new String[]{titulo1});
                    DB.crearnuevareceta(titulo.getText().toString(),pasos.getText().toString());
                    Toast.makeText(modificarReceta.this, "Receta guardada", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(modificarReceta.this,MostrarRecetas.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(modificarReceta.this, "Un campo vacio", Toast.LENGTH_SHORT).show();
                }
                db.close();


            }
        });

    }
}