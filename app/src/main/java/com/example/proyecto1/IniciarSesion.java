package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IniciarSesion extends AppCompatActivity {

    private final static String CHANNEL_ID = "NOTIFICACION";
    private final static int NOTIFICACION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        Button volver = (Button) findViewById(R.id.is_bot_volver);
        Button iniciar = (Button) findViewById(R.id.is_bot_iniciar);
        Button registrar = (Button) findViewById(R.id.is_bot_reg);

        EditText usuario = (EditText) findViewById(R.id.is_name);
        EditText pass = (EditText) findViewById(R.id.is_pass);

        //Boton que comprueba que el campo de usuario y contraseña no esten vacios y que sean correctos
        //si existe el usuario y la contraseña es la correcta se accede a la aplicacion y se muestra una notificacion
        // de bienvenida
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miDB DB = new miDB(getApplicationContext(), "Usuar.db", null, 1);
                SQLiteDatabase db = DB.getWritableDatabase();

                boolean ex = DB.existeusuario(usuario.getText().toString());

                if (usuario.getText().toString().isEmpty()) {
                    Toast.makeText(IniciarSesion.this, "Rellene el campo de Name con el usuario", Toast.LENGTH_SHORT).show();
                } else if (pass.getText().toString().isEmpty()) {
                    Toast.makeText(IniciarSesion.this, "Rellene el campo de Password con la contraseña", Toast.LENGTH_SHORT).show();
                } else {
                    if (ex) {
                        boolean m = DB.comprobarContraseña(usuario.getText().toString(), pass.getText().toString());
                        if (m) {
                            crearNotificacion(usuario.getText().toString());
                            Intent intent = new Intent(IniciarSesion.this, Home.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(IniciarSesion.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(getApplicationContext(), "No existe el usuario introducido", duration);
                        toast.show();
                    }
                }
            }
        });

        //boton que te lleva a la activity Registro para registrarte
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IniciarSesion.this, Registro.class);
                startActivity(intent);

            }
        });

        //Boton que te lleva a la pagina de presentacion
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IniciarSesion.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    //Metodo auxiliar para mostrar la notificacion
    @SuppressLint("MissingPermission")
    private void crearNotificacion(String usu) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "Notificacion", NotificationManager.IMPORTANCE_DEFAULT );
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.notificacion);
        builder.setContentTitle("Bienvenido de vuelta");
        builder.setContentText(""+usu);
        builder.setColor(Color.YELLOW);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setLights(Color.YELLOW, 1000, 1000);
        builder.setDefaults(Notification.DEFAULT_SOUND);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICACION_ID, builder.build());
    }

}