package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MostrarPlatos extends AppCompatActivity {

    private ListView l1;
    private ArrayList<Platos> lista = new ArrayList<Platos>();
    private Button volver;

    private  Platos plato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_platos);
        ///hacer el codigo para poner el color que este guardado en la instancia

        l1 = (ListView) findViewById(R.id.mp_listado);
        volver = findViewById(R.id.mp_volver);

        Intent x=getIntent();
        Integer dia=x.getIntExtra("dia",1);
        Integer mes=x.getIntExtra("mes",1);
        Integer año=x.getIntExtra("año",1);

        miDB DB = new miDB(getApplicationContext(), "Usuar.db", null, 1);
        SQLiteDatabase db = DB.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM t_platos WHERE dia='"+dia+"' AND mes='"+mes+"' AND año='"+año +"'", null);
        Toast.makeText(this, ""+dia+" "+mes+" "+año, Toast.LENGTH_SHORT).show();
        if (c.moveToFirst()) {
            do {
                plato= new Platos(c.getInt(1),c.getInt(2),c.getInt(3),c.getString(4));
                lista.add(plato);
                Toast.makeText(this, c.getInt(1)+" "+c.getInt(2)+" "+c.getInt(3)+" "+c.getInt(4), Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());

        }else {
            Toast.makeText(this, "No se encontraron platos para la fecha indicada", Toast.LENGTH_SHORT).show();
        }

        ArrayAdapter<Platos> adap = new ArrayAdapter<Platos>(getApplicationContext(), android.R.layout.simple_list_item_1, lista);
        l1.setAdapter(adap);
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor c = db.rawQuery("SELECT * FROM t_platos WHERE dia='"+dia+"' AND mes='"+mes+"' AND año='"+año +"'", null);
                if (c.moveToFirst()) {
                    while(i>0){
                        c.moveToNext();
                        i--;
                    }
                    String uno = c.getString(4);
                    Toast.makeText(MostrarPlatos.this, uno, Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(MostrarPlatos.this,ModificarPlato.class);
                    intent.putExtra("titulo",uno);

                    startActivity(intent);
                }
            }
        });

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MostrarPlatos.this, Home.class);
                startActivity(intent);

            }
        });
    }
}