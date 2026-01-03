package com.example.navigation_drawer_app.Fragmentos;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigation_drawer_app.Adaptadores.AdaptadorVideojuego;
import com.example.navigation_drawer_app.Actividades.NoDisponible;
import com.example.navigation_drawer_app.Altas.VideojuegoAltas;
import com.example.navigation_drawer_app.Clases.Listener;
import com.example.navigation_drawer_app.Clases.SesionManager;
import com.example.navigation_drawer_app.Clases.VideojuegoDAO;
import com.example.navigation_drawer_app.Edits.VideojuegoEdits;
import com.example.navigation_drawer_app.R;

import java.util.ArrayList;


public class VideojuegosFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener, Listener {

    String videojuego[], genero[], plataforma[] ;
    int imagen[]={R.drawable.halo}, ids[];
    ListView listita;
    FrameLayout deleteLayout;
    ImageView mas;
    TextView añadir;
    VideojuegoDAO videojuegoDAO;
    AdaptadorVideojuego adaptadorVideojuego;
    SesionManager sesionManager;
    Listener listener;
    Button cancelar, eliminar;
    int videojuegoSeleccionado=-1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_videojuegos, container, false);

        listita=view.findViewById(R.id.listita);
        mas=view.findViewById(R.id.mas);
        añadir=view.findViewById(R.id.añadir);
        sesionManager=new SesionManager(requireContext());
        deleteLayout=view.findViewById(R.id.deletelayout);
        cancelar=view.findViewById(R.id.cancelar);
        eliminar=view.findViewById(R.id.eliminar);

        listita.setOnItemClickListener(this);
        mas.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        eliminar.setOnClickListener(this);

        videojuego= new String[0];
        genero=new String[0];
        plataforma=new String[0];
        ids=new int[0];
        listener=this;
        videojuegoDAO=new VideojuegoDAO(requireContext());
        adaptadorVideojuego= new AdaptadorVideojuego(requireContext(), videojuego, genero, plataforma, imagen, ids, getLayoutInflater(), listener);
        videojuegoDAO= new VideojuegoDAO(requireContext());
        listita.setAdapter(adaptadorVideojuego);
        refreshVideojuegos();


        boolean login= sesionManager.isAdmin();

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
        if(mas.getId()==id)
        {
            Intent intent= new Intent(requireContext(), VideojuegoAltas.class);
            startActivity(intent);
        }
        else if(cancelar.getId()==id)
        {
            toggleDeleteLayout();
        }
        else if(eliminar.getId()==id)
        {
            boolean borrado=videojuegoDAO.eliminar(videojuegoSeleccionado);
            if(borrado)
            {
                Toast.makeText(requireContext(), "El videojuego se borro correctamente", Toast.LENGTH_SHORT).show();
                toggleDeleteLayout();
                refreshVideojuegos();
            }
            else
            {
                Toast.makeText(requireContext(), "Error al borrar", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(requireContext(), "El videojuego es "+videojuego[position]+ " su genero es "+genero[position]+" su plataforma es "+plataforma[position], Toast.LENGTH_SHORT).show();
    }

    public void refreshVideojuegos()
    {
        ArrayList<String>videojuegos=videojuegoDAO.verVideojuegos();

        videojuego=new String[videojuegos.size()];
        genero=new String[videojuegos.size()];
        plataforma=new String[videojuegos.size()];
        ids=new int[videojuegos.size()];
        imagen= new int[videojuegos.size()];

        for (int i=0; i<videojuegos.size(); i++)
        {
            String[] partes=videojuegos.get(i).split(";");
            ids[i]=Integer.parseInt(partes[0]);
            videojuego[i]=partes[1];
            genero[i]=partes[2];
            plataforma[i]=partes[3];
            imagen[i]=R.drawable.halo;
        }
        adaptadorVideojuego.UpdateVideojuego(videojuego, genero, plataforma, ids, imagen);
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshVideojuegos();
    }

    @Override
    public void onEditar(int position) {
        Intent intent= new Intent(requireContext(), VideojuegoEdits.class);
        intent.putExtra("videojuego", videojuego[position]);
        intent.putExtra("genero",genero[position]);
        intent.putExtra("plataforma",plataforma[position]);
        intent.putExtra("id",ids[position]);
        startActivity(intent);
    }

    @Override
    public void onBorrar(int position) {
        toggleDeleteLayout();
        videojuegoSeleccionado=ids[position];
    }

    public void toggleDeleteLayout()
    {
        if(deleteLayout.getVisibility()==View.VISIBLE)
        {
            deleteLayout.setVisibility(View.GONE);
        }
        else
        {
            deleteLayout.setVisibility(View.VISIBLE);
        }
    }
}