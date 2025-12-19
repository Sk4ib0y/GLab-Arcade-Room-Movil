package com.example.navigation_drawer_app.Fragmentos;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigation_drawer_app.Actividades.NoDisponible;
import com.example.navigation_drawer_app.Adaptadores.AdaptadorConsolaPrecio;
import com.example.navigation_drawer_app.Altas.ConsolaAltas;
import com.example.navigation_drawer_app.Clases.SesionManager;
import com.example.navigation_drawer_app.R;


public class ConsolaPrecioFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {


    ListView listita;
    ImageView mas;
    TextView añadir;
    int icono[]={R.drawable.xbox};
    String consola[]={"Xbox"}, jugadores[]={"2"}, precio[]={"50"};
    SesionManager sesionManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_consolas, container, false);

        añadir=view.findViewById(R.id.añadir);
        listita=view.findViewById(R.id.listita);
        mas=view.findViewById(R.id.mas);
        AdaptadorConsolaPrecio adaptadorConsolaPrecio = new AdaptadorConsolaPrecio(requireContext(), consola, jugadores, precio, icono, getLayoutInflater() );
        listita.setAdapter(adaptadorConsolaPrecio);
        listita.setOnItemClickListener(this);
        mas.setOnClickListener(this);
        sesionManager=new SesionManager(requireContext());

        boolean login=sesionManager.isAdmin();

        if(!login)
        {
            mas.setVisibility(View.GONE);
            añadir.setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        int id= v.getId();

        if(id==mas.getId())
        {
            Intent intent= new Intent(requireContext(), NoDisponible.class);
            startActivity(intent);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), "La consola es "+consola[position]+" pueden jugar "+jugadores[position]+" personas"+ " y cuesta "+precio[position]+" por hora ", Toast.LENGTH_SHORT).show();
    }
}