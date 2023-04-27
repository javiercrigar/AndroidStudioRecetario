package com.example.proyecto1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrarUsuario extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {
    EditText usuario, pass, gmail;
    Button volver, continuar;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarusuario);

        usuario=(EditText) findViewById(R.id.reg_name);
        pass=(EditText) findViewById(R.id.reg_pass);
        gmail=(EditText) findViewById(R.id.reg_gmail);
        continuar= (Button) findViewById(R.id.reg_bot_next);
        volver= (Button) findViewById(R.id.reg_bot_volver);

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar();
            }
        });
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegistrarUsuario.this,login.class);
                startActivity(intent);
            }
        });
    }
    private void registrar() {
        String nombre = usuario.getText().toString();
        String email = gmail.getText().toString();
        String contraseña = pass.getText().toString();

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("cargando...");

        if(nombre.isEmpty()){
            usuario.setError("complete los campos");
            return;
        }
        else if (contraseña.isEmpty()) {
            pass.setError("complete los campos");
            return;
        }
        else if(email.isEmpty()){
            gmail.setError("complete los campos");
            return;
        }
        else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "http://ec2-54-93-62-124.eu-central-1.compute.amazonaws.com/jcriado004/WEB/Registrar.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if(response.equalsIgnoreCase("El usuario ha sido registrado correctamente")){

                                Toast.makeText(RegistrarUsuario.this, "El usuario ha sido registrado correctamente", Toast.LENGTH_SHORT).show();

                                progressDialog.dismiss();

                                Intent intent=new Intent(RegistrarUsuario.this, login.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(RegistrarUsuario.this, response, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                Toast.makeText(RegistrarUsuario.this, "No se puede insertar", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RegistrarUsuario.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("usuario",nombre);
                    params.put("gmail",email);
                    params.put("pass",contraseña);

                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(RegistrarUsuario.this);
            requestQueue.add(request);
        }
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(), " NO FUNCIONA", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getApplicationContext(), "FUNCIONA", Toast.LENGTH_SHORT).show();
    }
}