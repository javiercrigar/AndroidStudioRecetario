package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button boton = (Button) findViewById(R.id.main_bot);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //miBD base = new miBD(getApplicationContext(),"Usuarios.db",null,1);

                Intent intent = new Intent(MainActivity.this,IniciarSesion.class);
                intent.putExtra("titulo",usuario);
                startActivity(intent);
            }
        });
    }
}