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


public class VideojuegosFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    String videojuego[]={"Halo"}, genero[]={"Shooter"}, plataforma[]={"Xbox"} ;
    int imagen[]={R.drawable.halo};
    ListView listita;
    ImageView mas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_videojuegos, container, false);
        listita=view.findViewById(R.id.listita);
        mas=view.findViewById(R.id.mas);

        listita.setOnItemClickListener(this);
        mas.setOnClickListener(this);
        AdaptadorVideojuego adaptadorVideojuego= new AdaptadorVideojuego(requireContext(), videojuego,genero,plataforma,imagen, getLayoutInflater());
        listita.setAdapter(adaptadorVideojuego);




        return view;
    }

    @Override
    public void onClick(View v) {
        int id= v.getId();
        if(mas.getId()==id)
        {
            Intent intent= new Intent(requireContext(), NoDisponible.class);
            startActivity(intent);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(requireContext(), "El videojuego es "+videojuego[position]+ " su genero es "+genero[position]+" su plataforma es "+plataforma[position], Toast.LENGTH_SHORT).show();
    }
}