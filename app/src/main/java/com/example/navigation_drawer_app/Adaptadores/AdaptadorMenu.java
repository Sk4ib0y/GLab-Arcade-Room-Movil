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
import com.example.navigation_drawer_app.Edits.MenuEdits;
import com.example.navigation_drawer_app.Fragmentos.MenuFragment;
import com.example.navigation_drawer_app.R;
import com.example.navigation_drawer_app.Clases.SesionManager;

public class AdaptadorMenu extends BaseAdapter
{
    Context context;
    String nombre[], precio[];
    LayoutInflater layoutInflater;
    SesionManager sesionManager;
    MenuFragment menuFragment;

    public AdaptadorMenu(Context context, String[] nombre, String[] precio, LayoutInflater layoutInflater, MenuFragment menuFragment) {
        this.context = context;
        this.nombre = nombre;
        this.precio = precio;
        this.layoutInflater = layoutInflater;
        this.sesionManager = new SesionManager(context);
        this.menuFragment=menuFragment;
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

        boolean login=sesionManager.isAdmin();

        TextView producto=convertView.findViewById(R.id.texto);
        TextView precioo=convertView.findViewById(R.id.precio);
        ImageView basura=convertView.findViewById(R.id.basura);
        ImageView editar=convertView.findViewById(R.id.editar);

        producto.setText(nombre[position]);
        precioo.setText(precio[position]);


        if (!login)
        {
            editar.setVisibility(View.GONE);
            basura.setVisibility(View.GONE);
        }

        else {
            editar.setOnClickListener(v -> {
                Intent intent = new Intent(context, MenuEdits.class);
                intent.putExtra("producto",nombre[position]);
                intent.putExtra("precio",precio[position]);
                context.startActivity(intent);
            });

            basura.setOnClickListener(v -> {
                menuFragment.itemSeleccionado=position;
                menuFragment.toggleDeleteLayout();
            });

        }


        return convertView;
    }
    public void updateView(String[] nuevoElemento, String[] nuevoPrecio)
    {
        this.nombre=nuevoElemento;
        this.precio=nuevoPrecio;
    }
}
