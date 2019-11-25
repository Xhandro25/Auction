package com.example.auctionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Bienvenido extends AppCompatActivity {

    Button btnirproductos;
    Button btncerrarsesion;
    Button btnmirar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);
        final TextView mensaje = findViewById(R.id.mensaje);
        Intent i = this.getIntent(); //Para salvaer la informacion que viene como extra
        String nombre = i.getStringExtra("nombre");
        mensaje.setText(mensaje.getText()+ " " + nombre);

        btnmirar = (Button) findViewById(R.id.btnmirar);
        btnirproductos = (Button)findViewById(R.id.btnirproductos);
        btncerrarsesion = (Button)findViewById(R.id.btncerrarsesion);

        btncerrarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cerrar = new Intent(Bienvenido.this, login_activity.class);
                startActivity(cerrar);
            }
        });

        btnirproductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cerrar = new Intent(Bienvenido.this, registro_productos.class);
                startActivity(cerrar);
            }
        });

        btnmirar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mirar = new Intent(Bienvenido.this, MainActivity.class);
                startActivity(mirar);
            }
        });
    }
}
