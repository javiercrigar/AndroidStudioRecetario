package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {
    EditText usuario, contraseña;
    String elusuario,lacontraseña;
    String url = "http://ec2-54-93-62-124.eu-central-1.compute.amazonaws.com/jcriado004/WEB/login.php";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = findViewById(R.id.is_name);
        contraseña = findViewById(R.id.is_pass);
    }

    public void IniciarSesion(View view) {

        if(usuario.getText().toString().equals("")){
            Toast.makeText(this, "Introduce el usuario", Toast.LENGTH_SHORT).show();
        }
        else if(contraseña.getText().toString().equals("")){
            Toast.makeText(this, "Introduce la contraseña", Toast.LENGTH_SHORT).show();
        }
        else{
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Por favor espera...");

            progressDialog.show();

            elusuario = usuario.getText().toString().trim();
            lacontraseña = contraseña.getText().toString().trim();


            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();

                    if(response.equalsIgnoreCase("ingreso correctamente")){
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        Toast.makeText(login.this, response, Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(login.this, response, Toast.LENGTH_SHORT).show();
                    }
                }
            },new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("usuario",elusuario);
                    params.put("pass",lacontraseña);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(login.this);
            requestQueue.add(request);
        }
    }

    public void Registrarse(View view) {
        startActivity(new Intent(getApplicationContext(), RegistrarUsuario.class));
        finish();
    }


}