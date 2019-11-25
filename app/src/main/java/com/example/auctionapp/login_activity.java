package com.example.auctionapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import static android.widget.Toast.LENGTH_SHORT;

public class login_activity extends AppCompatActivity {

    EditText txtusernamelogin, txtpasslogin;
    Button btnregistrologin, btniniciarsesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        txtusernamelogin = (EditText)findViewById(R.id.txtusernamelogin);
        txtpasslogin = (EditText)findViewById(R.id.txtpasslogin);

        btnregistrologin = findViewById(R.id.btnregistrologin);
        btnregistrologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent para que me redireccione al registro
                Intent irregistro = new Intent(login_activity.this, registro_activity.class);
                startActivity(irregistro);
            }
        });


        btniniciarsesion = (Button)findViewById(R.id.btniniciar);
        btniniciarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = txtusernamelogin.getText().toString();
                final String pass = txtpasslogin.getText().toString();

                Response.Listener<String> respuesta = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonrespuesta = new JSONObject(response);
                            boolean ok = jsonrespuesta.getBoolean("success");
                            if(ok==true){
                                String nombre = jsonrespuesta.getString("nombre");
                                Intent bienvenido = new Intent(login_activity.this, Bienvenido.class);
                                bienvenido.putExtra("nombre", nombre);
                                startActivity(bienvenido);
                            }else{
                               AlertDialog.Builder alerta = new AlertDialog.Builder(login_activity.this);
                                alerta.setMessage("Usuario y/o contraseña incorrectos")
                                        .setNegativeButton("Reintentar", null)
                                        .create().show();
                               // Toast.makeText(login_activity.this, "Usuario y/o contraseña incorrectos", LENGTH_SHORT).show();
                            }
                        }catch (JSONException e){
                            e.getMessage();
                        }
                    }
                };
                login_request r =  new login_request(username,pass,respuesta);
                RequestQueue requestQueue = Volley.newRequestQueue(login_activity.this);
                requestQueue.add(r);
            }
        });
    }
}
