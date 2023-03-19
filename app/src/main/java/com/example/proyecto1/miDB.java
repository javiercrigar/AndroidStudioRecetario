package com.example.proyecto1;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class miDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "Usuar.db";
    private static final String TABLE_USUARIOS = "t_usuarios";
    private static final String TABLE_RECETAS = "t_recetas";
    private static final String TABLE_PLATOS = "t_platos";

    public miDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USUARIOS + "(name TEXT NOT NULL PRIMARY KEY, pass TEXT NOT NULL, gmail TEXT NOT NULL)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_RECETAS + "(id INTEGER PRIMARY KEY AUTOINCREMENT , titulo TEXT NOT NULL, pasos TEXT NOT NULL)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PLATOS + "(id INTEGER PRIMARY KEY AUTOINCREMENT , dia INTEGER NOT NULL, mes INTEGER NOT NULL, año INTEGER NOT NULL, plato TEXT NOT NULL)");    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_USUARIOS);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_RECETAS);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_PLATOS);

        onCreate(sqLiteDatabase);
    }

    //comprueba si el usuario ya esta registrado
    public boolean existeusuario(String usu) {
        SQLiteDatabase bd = getWritableDatabase();
        boolean enc = false;
        if (usu.isEmpty() == false) {
            Cursor cursor = bd.query(TABLE_USUARIOS, new String[]{"name"},
                    "name" + "=?", new String[]{String.valueOf(usu)},
                    null, null, null, null);

            int count = cursor.getCount();
            cursor.close();
            bd.close();
            enc = count > 0;
        }
        return enc;
    }


    //Crea una nueva receta
    public void crearnuevareceta(String name, String pasos) {
        SQLiteDatabase bd = getWritableDatabase();

        if (name.isEmpty() == false && pasos.isEmpty() == false) {
            ContentValues nuevo = new ContentValues();
            nuevo.put("titulo", name);
            nuevo.put("pasos", pasos);
            bd.insert(TABLE_RECETAS, null, nuevo);
        }
        bd.close();
    }
    //crea un nuevo plato
    public void crearnuevoplato(Integer dia,Integer mes, Integer año,String plato) {
        SQLiteDatabase bd = getWritableDatabase();
        if (dia == null) {
            Log.d("crearnuevoplato", "El valor de dia es null");
        }
        if (plato.isEmpty() == false ) {
            ContentValues nuevo = new ContentValues();
            nuevo.put("dia", dia);
            nuevo.put("mes", mes);
            nuevo.put("año", año);
            nuevo.put("plato", plato);
            bd.insert(TABLE_PLATOS, null, nuevo);
        }
        bd.close();
    }
    //Añade un nuevo usuario a la base de datos con su contraseña
    public void añadir(String name, String pass, String gmail) {
        SQLiteDatabase bd = getWritableDatabase();

        if (name.isEmpty() == false && pass.isEmpty() == false && gmail.isEmpty() == false) {
            ContentValues nuevo = new ContentValues();
            nuevo.put("name", name);
            nuevo.put("pass", pass);
            nuevo.put("gmail", gmail);
            bd.insert(TABLE_USUARIOS, null, nuevo);
        }
        bd.close();
    }

    //Comprueba si la contraseña es correcta
    public boolean comprobarContraseña(String usu, String contra){

        SQLiteDatabase db=getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM t_usuarios ",null);
        if (c.moveToFirst()) {}
        String dos = c.getString((1));
        c.close();
        db.close();
        return dos.equals(contra);

    }
}




