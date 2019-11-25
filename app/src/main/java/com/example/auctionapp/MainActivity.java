package com.example.auctionapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etBuscador;

    private static final String URL = "http://auctionapp.x10.mx/AuctionApp/catalogo.php";

    List<Productos> productoslist;

    RecyclerView recyclerView;

    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etBuscador = findViewById(R.id.etBuscador);
        etBuscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                filtrar(s.toString());
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
       // recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productoslist = new ArrayList<>();

        loadproductos();

        adapter = new Adapter(MainActivity.this, productoslist);
        recyclerView.setAdapter(adapter);
    }

    private void loadproductos() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject productos = array.getJSONObject(i);

                                productoslist.add(new Productos(

                                        productos.getString("id_producto"),
                                        productos.getString("nombre_producto"),
                                        productos.getString("precio"),
                                        productos.getString("descripcion"),
                                        productos.getString("foto"),
                                        productos.getString("id_usuario"),
                                        productos.getString("id_proovedor"),
                                        productos.getString("id_categoria"),
                                        productos.getString("id_subcategoria")
                                ));

                            }
                            adapter = new Adapter(MainActivity.this, productoslist);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    public void filtrar(String texto) {
        ArrayList<Productos> filtrarLista = new ArrayList<>();

        for(Productos productos : productoslist) {
            //toloewr case devuelve cadena convertida en minusculas
            if(productos.getNombre_producto().toLowerCase().contains(texto.toLowerCase())) {
                filtrarLista.add(productos);
            }
        }
        adapter.filtrar(filtrarLista);
    }

}
