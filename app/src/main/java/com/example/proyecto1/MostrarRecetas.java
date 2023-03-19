package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.R.layout;
import android.widget.Toast;

import java.util.ArrayList;

public class MostrarRecetas extends AppCompatActivity {
    private ListView l1;
    private ArrayList<Recetas> lista = new ArrayList<Recetas>();
    private Recetas recetas;
    private Button volver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_recetas);
        ///hacer el codigo para poner el color que este guardado en la instancia

        l1 = (ListView) findViewById(R.id.mr_listado);
        volver = findViewById(R.id.mr_volver);

        miDB DB = new miDB(getApplicationContext(), "Usuar.db", null, 1);
        SQLiteDatabase db = DB.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM t_recetas", null);

        if (c.moveToFirst()) {
            do {
                recetas = new Recetas(c.getString(1), c.getString(2));
                lista.add(recetas);
            } while (c.moveToNext());
        }
        ArrayAdapter<Recetas> adap = new ArrayAdapter<Recetas>(getApplicationContext(), layout.simple_list_item_1, lista);
        l1.setAdapter(adap);
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Cursor c = db.rawQuery("SELECT * FROM t_recetas ", null);
               if (c.moveToFirst()) {
                   while(i>0){
                       c.moveToNext();
                       i--;
                   }
               String uno = c.getString(1);
               String dos = c.getString(2);
               Toast.makeText(MostrarRecetas.this, uno + dos, Toast.LENGTH_SHORT).show();
               Intent intent= new Intent(MostrarRecetas.this,modificarReceta.class);
               intent.putExtra("titulo",uno);
               intent.putExtra("pasos",dos);
               startActivity(intent);
               }
            }
        });

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MostrarRecetas.this, Home.class);
                startActivity(intent);

            }
        });
    }
}