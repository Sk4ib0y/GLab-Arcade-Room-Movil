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

import com.example.navigation_drawer_app.Adaptadores.AdaptadorMenu;
import com.example.navigation_drawer_app.Actividades.NoDisponible;
import com.example.navigation_drawer_app.Clases.MenuDAO;
import com.example.navigation_drawer_app.Formularios.MenuAltas;
import com.example.navigation_drawer_app.R;
import com.example.navigation_drawer_app.Clases.SesionManager;

import java.util.ArrayList;


public class MenuFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    String producto[]={"maruchan", "monster", "doritos", "nachos", "cerveza"}, precio[]={"20","40","25","45","50"};
    ListView listita;
    TextView añadir;
    ImageView mas;
    MenuDAO menuDAO;
    SesionManager sesionManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_menu,container, false);
        listita=view.findViewById(R.id.listita);
        mas=view.findViewById(R.id.mas);
        añadir=view.findViewById(R.id.añadir);

        listita.setOnItemClickListener(this);
        mas.setOnClickListener(this);
        sesionManager=new SesionManager(requireContext());

        ArrayList<String> productos=menuDAO.verProductos();
        String[]elementos=new String[productos.size()];
        String[]precios=new String[productos.size()];

        for(int i=0; i<productos.size(); i++)
        {
            String[] partes= productos.get(i).split(";");
            elementos[i]=partes[0];
            precios[i]=partes[1];
        }

        AdaptadorMenu adaptadorMenu= new AdaptadorMenu(requireContext(), elementos, precios, getLayoutInflater());
        listita.setAdapter(adaptadorMenu);

        boolean login=sesionManager.isAdmin();


        if(!login)
        {
            mas.setVisibility(View.GONE);
            añadir.setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(requireContext(), "El producto es "+producto[position] +" y cuesta $"+precio[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int id= v.getId();
        if(mas.getId()== id)
        {
            Intent intent= new Intent(requireContext(), MenuAltas.class);
            requireContext().startActivity(intent);
        }
    }
}