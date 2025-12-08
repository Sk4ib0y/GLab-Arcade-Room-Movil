package com.example.navigation_drawer_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdaptadorMenu extends BaseAdapter
{
    Context context;
    String nombre[], precio[];
    LayoutInflater layoutInflater;

    public AdaptadorMenu(Context context, String[] nombre, String[] precio, LayoutInflater layoutInflater) {
        this.context = context;
        this.nombre = nombre;
        this.precio = precio;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return nombre.length;
    }

    @Override
    public Object getItem(int position) {
        return nombre[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=layoutInflater.inflate(R.layout.modelomenu, null);

        TextView producto=convertView.findViewById(R.id.texto);
        TextView precioo=convertView.findViewById(R.id.precio);
        ImageView basura=convertView.findViewById(R.id.basura);
        ImageView editar=convertView.findViewById(R.id.editar);

        producto.setText(nombre[position]);
        precioo.setText(precio[position]);

        editar.setOnClickListener(v -> {
            Intent intent= new Intent(context, NoDisponible.class);
            context.startActivity(intent);
        });

        basura.setOnClickListener(v -> {
            Intent intent= new Intent(context, NoDisponible.class);
            context.startActivity(intent);
        });

        return convertView;
    }
}
