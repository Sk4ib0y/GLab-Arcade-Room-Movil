package com.example.navigation_drawer_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


public class InventarioFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    int imagen[]={R.drawable.xbox};
    String consola[]={"Xbox"}, estado[]={"Disponible"}, observacion[]={"No funciona control"};
    ImageView mas;
    ListView listita;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_inventario, container, false);

        mas=view.findViewById(R.id.mas);
        listita=view.findViewById(R.id.listita);
        AdaptadorInventario adaptadorInventario= new AdaptadorInventario(requireContext(), consola, estado, observacion, imagen, getLayoutInflater());
        listita.setAdapter(adaptadorInventario);
        mas.setOnClickListener(this);
        listita.setOnItemClickListener(this);


        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(requireContext(), "La consola es "+consola[position]+  "\nEsta "+estado[position]+"\nalgunas observaciones "+observacion[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if(id==mas.getId())
        {
            Intent intent= new Intent(requireContext(), NoDisponible.class);
            startActivity(intent);
        }
    }
}