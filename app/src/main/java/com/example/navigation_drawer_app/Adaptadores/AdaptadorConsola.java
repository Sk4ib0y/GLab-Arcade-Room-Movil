package com.example.navigation_drawer_app.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.navigation_drawer_app.Clases.FragmentActions;
import com.example.navigation_drawer_app.Clases.Listener;
import com.example.navigation_drawer_app.Clases.SesionManager;
import com.example.navigation_drawer_app.R;

public class AdaptadorConsola extends BaseAdapter
{
    Context context;
    String consola[], observaciones[], estado[];
    int imagencita[], id[];
    LayoutInflater inflater;
    SesionManager sesionManager;
    Listener listener;
    boolean edicion;
    public AdaptadorConsola(Context context, String[] consola, String[] observaciones, String[] estado, int[] imagencita, int[] id, LayoutInflater inflater, Listener listener, boolean edicion) {

        this.context = context;
        this.consola = consola;
        this.observaciones = observaciones;
        this.estado = estado;
        this.imagencita = imagencita;
        this.id=id;
        this.inflater = inflater;
        this.sesionManager=new SesionManager(context);
        this.listener= listener;
        this.edicion=edicion;
    }

    @Override
    public int getCount() {
        return consola.length;
    }

    @Override
    public Object getItem(int position) {
        return consola[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=inflater.inflate(R.layout.modeloinventario, null);

        boolean login=sesionManager.isAdmin();

        TextView consolat=convertView.findViewById(R.id.texto);
        TextView estadot=convertView.findViewById(R.id.estado);
        TextView observaciont=convertView.findViewById(R.id.observaciones);
        ImageView imagen=convertView.findViewById(R.id.icono);
        ImageView edit=convertView.findViewById(R.id.editar);
        ImageView borrar=convertView.findViewById(R.id.basura);


        if(login && edicion && listener!=null)
        {
            edit.setVisibility(View.VISIBLE);
            borrar.setVisibility(View.VISIBLE);
            edit.setOnClickListener(v -> listener.onEditar(position));
            borrar.setOnClickListener(v -> listener.onBorrar(position));
        }
        else
        {
            edit.setVisibility(View.GONE);
            borrar.setVisibility(View.GONE);
        }

        consolat.setText(consola[position]);
        estadot.setText(estado[position]);
        observaciont.setText(observaciones[position]);
        imagen.setImageResource(imagencita[0]);
        return convertView;
    }

    public void UpdateConsolas(String[] nuevaConsola, String[] nuevoEstado, String[] nuevaObs, int[] nuevoId)
    {
        this.consola= nuevaConsola;
        this.estado=nuevoEstado;
        this.observaciones=nuevaObs;
        this.id=nuevoId;
        notifyDataSetChanged();
    }
}
