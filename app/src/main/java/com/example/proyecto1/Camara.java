package com.example.proyecto1;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import androidx.core.content.ContextCompat;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.net.Uri;

public class Camara extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_GALLERY = 2;
    Button btnBuscar, btnSubir, btnCamara, btnVolver;
    ImageView image;
    EditText et;
    byte[] imageByte;
    private static final int REQUEST_CAMERA_PERMISSION = 1;

    int PICK_IMAGE_REQUEST=1;
    //https://www.youtube.com/watch?v=p1HdvY5YeHE&ab_channel=HaranitGonzalez
    //codigo visto aqui

    private ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
            registerForActivityResult(new
                    ActivityResultContracts.PickVisualMedia(), uri -> {
                if (uri != null) {
                    Log.d("PhotoPicker", "Selected URI: " + uri);
                    image = findViewById(R.id.cam_imagen);
                    image.setImageURI(uri);
                    Bitmap bitmapFoto = null;
                    try {
                        bitmapFoto = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    int anchoDestino = image.getWidth();
                    int altoDestino = image.getHeight();
                    int anchoImagen = bitmapFoto.getWidth();
                    int altoImagen = bitmapFoto.getHeight();
                    float ratioImagen = (float) anchoImagen / (float) altoImagen;
                    float ratioDestino = (float) anchoDestino / (float) altoDestino;
                    int anchoFinal = anchoDestino;
                    int altoFinal = altoDestino;
                    if (ratioDestino > ratioImagen) {
                        anchoFinal = (int) ((float)altoDestino * ratioImagen);
                    } else {
                        altoFinal = (int) ((float)anchoDestino / ratioImagen);
                    }
                    Bitmap bitmapredimensionado = Bitmap.createScaledBitmap(bitmapFoto,anchoFinal,altoFinal,true);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmapredimensionado.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    image.setImageBitmap(bitmapredimensionado);
                    imageByte=stream.toByteArray();

                } else {
                    Log.d("PhotoPicker", "No media selected");
                }
            });

    private ActivityResultLauncher<Intent> takePictureLauncher =
            registerForActivityResult(new
                    ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK &&
                        result.getData()!= null) {
                    Bundle bundle = result.getData().getExtras();
                    image = findViewById(R.id.cam_imagen);
                    Bitmap laminiatura = (Bitmap) bundle.get("data");
                    int anchoDestino = image.getWidth();
                    int altoDestino = image.getHeight();
                    int anchoImagen = laminiatura.getWidth();
                    int altoImagen = laminiatura.getHeight();
                    float ratioImagen = (float) anchoImagen / (float) altoImagen;
                    float ratioDestino = (float) anchoDestino / (float) altoDestino;
                    int anchoFinal = anchoDestino;
                    int altoFinal = altoDestino;
                    if (ratioDestino > ratioImagen) {
                        anchoFinal = (int) ((float)altoDestino * ratioImagen);
                    } else {
                        altoFinal = (int) ((float)anchoDestino / ratioImagen);
                    }
                    Bitmap bitmapredimensionado = Bitmap.createScaledBitmap(laminiatura,anchoFinal,altoFinal,true);
                    image.setImageBitmap(bitmapredimensionado);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmapredimensionado.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    imageByte=stream.toByteArray();
                } else {
                    Log.d("TakenPicture", "No photo taken");
                }
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);
        btnBuscar = findViewById(R.id.cam_buscar);
        btnSubir = findViewById(R.id.cam_subir);

        et = (EditText) findViewById(R.id.cam_texto);
        image = findViewById(R.id.cam_imagen);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });

        btnSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subirImagen();
            }
        });

        btnCamara=findViewById(R.id.cam_tomar);
        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCamara();
            }
        });
        btnVolver = findViewById(R.id.cam_volver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Camara.this,Home.class);
                startActivity(intent);
            }
        });
        }
    public void abrirCamara(){
        Intent elIntentFoto= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureLauncher.launch(elIntentFoto);
    }
    private void showFileChooser(){
        pickMedia.launch(new PickVisualMediaRequest.Builder()
                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                .build());
    }
    public String getStringImagen(){
        String encodedImage = Base64.encodeToString(imageByte, Base64.DEFAULT);
        return encodedImage;

    }
    public void subirImagen(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Por favor espera...");

        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, "http://ec2-54-93-62-124.eu-central-1.compute.amazonaws.com/jcriado004/WEB/subirImagen.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equalsIgnoreCase("La imagen ha sido subida correctamente")){
                            Toast.makeText(Camara.this, "La imagen ha sido subida correctamente", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                        else{
                            Toast.makeText(Camara.this, response, Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Camara.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String imagen =getStringImagen();
                String nombre = et.getText().toString().trim();

                Map<String,String> params = new HashMap<>();
                params.put("foto",imagen);
                params.put("nombre",nombre);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            Uri filePath = data.getData();
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//                image.setImageBitmap(bitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        else{
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            image.setImageBitmap(imageBitmap);
//        }
//    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putByteArray("imagen", imageByte);

    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        imageByte= savedInstanceState.getByteArray("imagen");
        if (imageByte != null) {
//            byte[] byteArray = savedInstanceState.getByteArray("imagen");
//            if (byteArray != null) {
            Bitmap x = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
            image.setImageBitmap(x);
        }
    }
//        imageByte=savedInstanceState.getByteArray("imagen");
//        if(imageByte!=null){
//           Bitmap imagenBitMap = BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);
//
//           image.setImageBitmap(imagenBitMap);
//        }

}
