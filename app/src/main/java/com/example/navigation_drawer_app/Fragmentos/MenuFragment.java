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

import com.example.navigation_drawer_app.Adaptadores.AdaptadorMenu;
import com.example.navigation_drawer_app.Clases.MenuDAO;
import com.example.navigation_drawer_app.Altas.MenuAltas;
import com.example.navigation_drawer_app.R;
import com.example.navigation_drawer_app.Clases.SesionManager;

import java.util.ArrayList;


public class MenuFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    String[] elementos, precios;
    ListView listita;
    TextView añadir;
    ImageView mas;
    FrameLayout deleteLayout;
    Button cancelar, eliminar;
    MenuDAO menuDAO;
    SesionManager sesionManager;
    AdaptadorMenu adaptadorMenu;

    public int itemSeleccionado=-1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_menu,container, false);
        listita=view.findViewById(R.id.listita);
        mas=view.findViewById(R.id.mas);
        añadir=view.findViewById(R.id.añadir);
        deleteLayout=view.findViewById(R.id.deletelayout);
        cancelar=view.findViewById(R.id.cancelar);
        eliminar=view.findViewById(R.id.eliminar);


        cancelar.setOnClickListener(this);
        eliminar.setOnClickListener(this);

        listita.setOnItemClickListener(this);
        mas.setOnClickListener(this);
        sesionManager=new SesionManager(requireContext());
        menuDAO=new MenuDAO(requireContext());
        elementos=new String[0];
        precios=new String[0];
        adaptadorMenu= new AdaptadorMenu(requireContext(), elementos, precios, getLayoutInflater(),this);
        listita.setAdapter(adaptadorMenu);
        refreshList();

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
        Toast.makeText(requireContext(), "El producto es "+elementos[position] +" y cuesta $"+precios[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int id= v.getId();
        if(mas.getId()== id)
        {
            Intent intent= new Intent(requireContext(), MenuAltas.class);
            requireContext().startActivity(intent);
        }
        else if(cancelar.getId()==id)
        {
            toggleDeleteLayout();
        }
        else if(eliminar.getId()==id)
        {
            int posicion=itemSeleccionado;
            String producto=elementos[posicion];
            float precioF=Float.parseFloat(precios[posicion]);

            boolean borrado=menuDAO.eliminar(producto, precioF);

            if(borrado)
            {
                Toast.makeText(requireContext(), "Elemento borrado correctamente", Toast.LENGTH_SHORT).show();
                toggleDeleteLayout();
                refreshList();
            }
            else
            {
                Toast.makeText(requireContext(), "Error al borrar el elemento", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshList();
    }

    public void refreshList()
    {
        ArrayList<String> productos=menuDAO.verProductos();
        elementos=new String[productos.size()];
        precios=new String[productos.size()];

        for(int i=0; i<productos.size(); i++)
        {
            String[] partes= productos.get(i).split(";");
            elementos[i]=partes[0];
            precios[i]=partes[1];
        }
        adaptadorMenu.updateView(elementos, precios);
        adaptadorMenu.notifyDataSetChanged();
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