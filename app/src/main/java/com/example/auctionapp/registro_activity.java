package com.example.auctionapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.LENGTH_SHORT;

public class registro_activity extends AppCompatActivity {

    //variables que utilizaremos
    EditText txtnombreregistro, txtapellidosregistro, txtusernameregistro, txtpassregistro, txtpaisregistro, txtciudadregistro;
    Button btnregistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_activity);

        //Relacion entre parte logica y grafica
        txtnombreregistro = (EditText) findViewById(R.id.txtnombreregistro);
        txtapellidosregistro = (EditText) findViewById(R.id.txtapellidosregistro);
        txtusernameregistro = (EditText) findViewById(R.id.txtusernameregistro);
        txtpassregistro = (EditText) findViewById(R.id.txtpassregistro);
        txtpaisregistro = (EditText) findViewById(R.id.txtpaisregistro);
        txtciudadregistro = (EditText) findViewById(R.id.txtciudadregistro);
        btnregistro = (Button) findViewById(R.id.btnregistro);


        btnregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (txtnombreregistro.getText().toString().isEmpty()) {
                    Toast.makeText(registro_activity.this, "Campo nombre vacio", LENGTH_SHORT).show();
                } else {
                    if (txtapellidosregistro.getText().toString().isEmpty()) {
                        Toast.makeText(registro_activity.this, "Campo apellido vacio", LENGTH_SHORT).show();
                    } else {
                        if (txtusernameregistro.getText().toString().isEmpty()) {
                            Toast.makeText(registro_activity.this, "Campo username vacio", LENGTH_SHORT).show();
                        } else {
                            if (txtpassregistro.getText().toString().isEmpty()) {
                                Toast.makeText(registro_activity.this, "Campo contraseña vacio", LENGTH_SHORT).show();
                            } else {
                                if (txtpaisregistro.getText().toString().isEmpty()) {
                                    Toast.makeText(registro_activity.this, "Campo pais vacio", LENGTH_SHORT).show();
                                } else {
                                    if (txtciudadregistro.getText().toString().isEmpty()) {
                                        Toast.makeText(registro_activity.this, "Campo ciudad vacio", LENGTH_SHORT).show();
                                    } else {
                                        ejecutarservicio("http://auctionapp.x10.mx/AuctionApp/registro.php?nombre=" + txtnombreregistro.getText().toString()
                                                + "&apellido=" + txtapellidosregistro.getText().toString() + "&username=" + txtusernameregistro.getText().toString()
                                                + "&pass=" + txtpassregistro.getText().toString() + "&pais=" + txtpaisregistro.getText().toString()
                                                + "&ciudad=" + txtciudadregistro.getText().toString());
                                        Intent irregistro = new Intent(registro_activity.this, login_activity.class);
                                        startActivity(irregistro);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    //Metodo que enviara peticiones al servidor
    private void ejecutarservicio(String URL) {
        StringRequest stringrequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(registro_activity.this, "Datos ingresados correctamente",Toast.LENGTH_LONG).show();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("nombre", txtnombreregistro.getText().toString());
                parametros.put("apellido", txtapellidosregistro.getText().toString());
                parametros.put("username", txtusernameregistro.getText().toString());
                parametros.put("pass", txtpassregistro.getText().toString());
                parametros.put("pais", txtpaisregistro.getText().toString());
                parametros.put("ciudad", txtciudadregistro.getText().toString());
                return super.getParams();
            }
        };

        //Procesamos las peitciones para que la libreria las ejecute
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringrequest);
    }
}