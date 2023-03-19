package com.example.proyecto1;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class Registro extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Button volver = (Button) findViewById(R.id.reg_bot_volver);
        Button next=(Button) findViewById(R.id.reg_bot_next);

        EditText usuario = (EditText) findViewById(R.id.reg_name);
        EditText pass = (EditText) findViewById(R.id.reg_pass);
        EditText gmail = (EditText) findViewById(R.id.reg_gmail);


        //Boton para volver a la activity iniciar sesion
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registro.this,IniciarSesion.class);
                startActivity(intent);

            }
        });

        //Si el usuario ha rellenado todos los campos y el usuario introducido no esta escogido previamente por otra persona
        //añade es usuario a la base de datos
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int duration = Toast.LENGTH_SHORT;

                miDB DB =new miDB(getApplicationContext(),"Usuar.db",null,1);
                SQLiteDatabase db = DB.getWritableDatabase();

               boolean x= DB.existeusuario(usuario.getText().toString());

                if(usuario.getText().toString().isEmpty()){
                    Toast.makeText(Registro.this, "Rellene el campo de Name con el usuario", Toast.LENGTH_SHORT).show();
                } else if (pass.getText().toString().isEmpty()) {
                    Toast.makeText(Registro.this, "Rellene el campo de Password con la contraseña", Toast.LENGTH_SHORT).show();
                }else if (gmail.getText().toString().isEmpty()) {
                    Toast.makeText(Registro.this, "Rellene el campo de Gmail con el gmail", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(!x) {
                        DB.añadir(usuario.getText().toString(), pass.getText().toString(), gmail.getText().toString());

                        Toast toast1 = Toast.makeText(getApplicationContext(), "Usuario Registrado", duration);
                        toast1.show();
                        Intent intent = new Intent(Registro.this, Home.class);
                        startActivity(intent);
                    }
                    else{
                        Toast toast1 = Toast.makeText(getApplicationContext(), "Usuario previamente registrado seleccione otro nombre de usuario", duration);
                        toast1.show();
                    }
                }

            }
        });
    }
}
