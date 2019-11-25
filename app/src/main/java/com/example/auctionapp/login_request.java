package com.example.auctionapp;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class login_request extends StringRequest {
    private static final String ruta = "http://auctionapp.x10.mx/AuctionApp/iniciarsesion.php";
    private Map<String, String> parametros;

    public login_request (String username, String pass, Response.Listener<String> listener){
        super(Method.POST, ruta,listener,null);
        parametros = new HashMap<>();
        parametros.put("username", username + "");
        parametros.put("pass", pass + "");
    }

    @Override
    protected Map<String, String> getParams(){
        return parametros;
    }
}
