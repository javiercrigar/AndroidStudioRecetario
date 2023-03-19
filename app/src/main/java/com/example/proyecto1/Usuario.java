package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Usuario extends AppCompatActivity {

    private EditText usu;
    private EditText p1;
    private EditText p2;
    private EditText g;
    private EditText g2;
    private Button con;
    private Button gma;
    private Button volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        usu=findViewById(R.id.usu_n);
        p1=findViewById(R.id.usu_p1);
        p2=findViewById(R.id.usu_p2);
        g=findViewById(R.id.usu_gmail);
        g2=findViewById(R.id.usu_gmail2);
        con=findViewById(R.id.usu_bot_pass);
        gma=findViewById(R.id.usu_bot_gmail);
        volver=findViewById(R.id.usu_bot_volver);

        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarContraseña(p1.getText().toString(),p2.getText().toString(),usu.getText().toString());
            }
        });

        gma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarGmail(g.getText().toString(),g2.getText().toString());
            }
        });
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Usuario.this,Home.class);
                startActivity(intent);
            }
        });

    }

    private void cambiarContraseña(String p1, String p2, String n){
        if (!p1.equals(p2)){
            Toast.makeText(this, "La contraseñas no son iguales", Toast.LENGTH_SHORT).show();
        }else{
            miDB DB = new miDB(getApplicationContext(), "Usuar.db", null, 1);
            SQLiteDatabase db = DB.getWritableDatabase();
            String query = "UPDATE t_usuarios SET pass='" + p1 + "' WHERE name='"+n+"'";
            db.execSQL(query);
            db.close();
            Toast.makeText(this, "Contraseña modificada", Toast.LENGTH_SHORT).show();

        }
    }

    private void cambiarGmail(String p1, String n){

        miDB DB = new miDB(getApplicationContext(), "Usuar.db", null, 1);
        SQLiteDatabase db = DB.getWritableDatabase();
        String query = "UPDATE t_usuarios SET gmail='" + p1 + "' WHERE name='"+n+"'";
        db.execSQL(query);
        db.close();
        Toast.makeText(this, "Gmail modificado", Toast.LENGTH_SHORT).show();

    }
}