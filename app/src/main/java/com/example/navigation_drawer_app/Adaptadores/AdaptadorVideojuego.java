package com.example.navigation_drawer_app.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.navigation_drawer_app.Actividades.NoDisponible;
import com.example.navigation_drawer_app.Clases.Listener;
import com.example.navigation_drawer_app.Clases.SesionManager;
import com.example.navigation_drawer_app.R;

public class AdaptadorVideojuego extends BaseAdapter implements Listener
{
    Context context;
    String videojuego[], genero[], plataforma[];
    int imagencita[], ids[];
    LayoutInflater inflater;
    SesionManager sesionManager;
    Listener listener;
    public AdaptadorVideojuego(Context context, String[] videojuego, String[] genero, String[] plataforma, int[] imagencita, int[] ids, LayoutInflater inflater, Listener listener) {
        this.context = context;
        this.videojuego = videojuego;
        this.genero = genero;
        this.plataforma = plataforma;
        this.imagencita = imagencita;
        this.ids=ids;
        this.inflater = inflater;
        this.sesionManager=new SesionManager(context);
        this.listener=listener;
    }

    @Override
    public int getCount() {
        return videojuego.length;
    }

    @Override
    public Object getItem(int position) {
        return videojuego[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=inflater.inflate(R.layout.modelovideojuego, null);

        boolean login=sesionManager.isAdmin();

        TextView nombre=convertView.findViewById(R.id.texto);
        TextView generot=convertView.findViewById(R.id.genero);
        TextView plataformat=convertView.findViewById(R.id.plataforma);
        ImageView imagenv=convertView.findViewById(R.id.icono);
        ImageView edit=convertView.findViewById(R.id.editar);
        ImageView basura=convertView.findViewById(R.id.basura);


        if(!login)
        {
            edit.setVisibility(View.GONE);
            basura.setVisibility(View.GONE);
        }
        else {
            edit.setOnClickListener(v -> {listener.onEditar(position);
            });

            basura.setOnClickListener(v -> {listener.onBorrar(position);
            });

        }
        nombre.setText(videojuego[position]);
        generot.setText(genero[position]);
        plataformat.setText(plataforma[position]);
        imagenv.setImageResource(R.drawable.halo);
        return convertView;
    }

    public void UpdateVideojuego(String[] nuevoVideojuego, String[] nuevoGenero, String[] nuevaPlataforma, int[] nuevoId, int[] nuevaImagen)
    {
        this.videojuego=nuevoVideojuego;
        this.genero=nuevoGenero;
        this.plataforma=nuevaPlataforma;
        this.ids=nuevoId;
        this.imagencita=nuevaImagen;
        notifyDataSetChanged();
    }

    @Override
    public void onEditar(int position) {

    }

    @Override
    public void onBorrar(int position) {

    }
}
