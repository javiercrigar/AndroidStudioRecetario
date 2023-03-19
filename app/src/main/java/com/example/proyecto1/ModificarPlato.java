package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModificarPlato extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_plato);

        Button borrar=findViewById(R.id.modp_bot_borrar);
        Button volver=findViewById(R.id.modp_bot_voler);

        EditText titulo= findViewById(R.id.modp_nombre);

        Intent intent=getIntent();

        titulo.setText(intent.getStringExtra("titulo"));

        //Vuelve a Home
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ModificarPlato.this,Home.class);
                startActivity(intent);
            }
        });

        //Elimina el plato seleccionado
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miDB DB =new miDB(getApplicationContext(),"Usuar.db",null,1);
                SQLiteDatabase db = DB.getWritableDatabase();
                if(titulo.getText().toString().isEmpty()==false){
                db.delete("t_platos","plato=?",new String[]{titulo.getText().toString()});
                db.close();
                Toast.makeText(ModificarPlato.this, "Has borrado el plato", Toast.LENGTH_SHORT).show();

                Intent intent= new Intent(ModificarPlato.this,Home.class);
                startActivity(intent);

                }
                else{
                    Toast.makeText(ModificarPlato.this, "Un campo vacio", Toast.LENGTH_SHORT).show();
                }
                db.close();
            }
        });
    }
}