package com.example.auctionapp;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ProductosViewHolder> {

    private Context mCtx;
    private List<Productos> productoslist;

    public Adapter(Context mCtx, List<Productos> productoslist) {
        this.mCtx = mCtx;
        this.productoslist = productoslist;
    }


    @Override
    public ProductosViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout,null);

        return new ProductosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductosViewHolder holder, final int position){
        Productos productos = productoslist.get(position);
        //Cargamos la imagen
       Glide.with(mCtx).
               load(productos.getFoto()).
               into(holder.imageView);

        holder.tvNombre_Producto.setText(productos.getNombre_producto());
        holder.tvPrecio.setText(productos.getPrecio());
    }

    @Override
    public int getItemCount(){
        return productoslist.size();
    }



    public class ProductosViewHolder extends RecyclerView.ViewHolder {
        //Enlazamos nuestro textview segun nuestra interfaz list
        ImageView imageView;
        TextView tvNombre_Producto;
        TextView tvPrecio;
        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);
            //Enlazamos interfaz con logica
            imageView = itemView.findViewById(R.id.imageView);
            tvNombre_Producto = itemView.findViewById(R.id.tvnombre_producto);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
        }
    }

    public void filtrar(ArrayList<Productos> filtrarproductos) {
        this.productoslist = filtrarproductos;
        notifyDataSetChanged(); //Actualizar datos de la lista
    }

}