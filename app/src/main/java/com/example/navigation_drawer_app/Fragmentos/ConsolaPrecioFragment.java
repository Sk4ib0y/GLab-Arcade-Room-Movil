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

import com.example.navigation_drawer_app.Actividades.NoDisponible;
import com.example.navigation_drawer_app.Adaptadores.AdaptadorConsolaPrecio;
import com.example.navigation_drawer_app.Altas.ConsolaPrecioAltas;
import com.example.navigation_drawer_app.Clases.ConsolaPrecioDAO;
import com.example.navigation_drawer_app.Clases.Listener;
import com.example.navigation_drawer_app.Clases.SesionManager;
import com.example.navigation_drawer_app.Edits.ConsolaPrecioEdits;
import com.example.navigation_drawer_app.R;

import java.util.ArrayList;


public class ConsolaPrecioFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener, Listener {


    ListView listita;
    FrameLayout deleteLayout;
    ImageView mas;
    Button delete, cancelar;
    TextView añadir;
    int icono[]={R.drawable.xbox}, ids[];
    int consolaSeleccionada=-1;
    String consola[], jugadores[], precio[];
    ConsolaPrecioDAO consolaPrecioDAO;
    AdaptadorConsolaPrecio adaptadorConsolaPrecio;
    Listener listener;


    SesionManager sesionManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_consolas, container, false);

        añadir=view.findViewById(R.id.añadir);
        listita=view.findViewById(R.id.listita);
        mas=view.findViewById(R.id.mas);
        deleteLayout=view.findViewById(R.id.deletelayout);
        delete=view.findViewById(R.id.eliminar);
        cancelar=view.findViewById(R.id.cancelar);
        
        delete.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        mas.setOnClickListener(this);
        sesionManager=new SesionManager(requireContext());
        
        consola=new String[0];
        jugadores=new String[0];
        precio=new String[0];
        ids=new int[0];
        listener=this;
        consolaPrecioDAO=new ConsolaPrecioDAO(requireContext());
        adaptadorConsolaPrecio= new AdaptadorConsolaPrecio(requireContext(), consola, jugadores, precio, icono, ids, getLayoutInflater(), listener);
        boolean login=sesionManager.isAdmin();
        listita.setAdapter(adaptadorConsolaPrecio);
        refreshConsolaPrecio();
        listita.setOnItemClickListener(this);

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
            Intent intent= new Intent(requireContext(), ConsolaPrecioAltas.class);
            startActivity(intent);
        }
        
        else if(id==cancelar.getId())
        {
            toggleDeleteLayout();
        }
        else if(id==delete.getId())
        {
            int idReal=ids[consolaSeleccionada];
            boolean borrado=consolaPrecioDAO.eliminar(idReal);
            if(borrado)
            {
                Toast.makeText(requireContext(), "El precio de la consola se elimino correctamente", Toast.LENGTH_SHORT).show();
                toggleDeleteLayout();
                refreshConsolaPrecio();
            }
            else
            {
                Toast.makeText(requireContext(), "Error eliminando el precio", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), "La consolaprecio es "+ consola[position]+" pueden jugar "+jugadores[position]+" personas"+ " y cuesta "+precio[position]+" por hora ", Toast.LENGTH_SHORT).show();
    }

    public void refreshConsolaPrecio()
    {
        ArrayList<String>consolaPrecio=consolaPrecioDAO.verConsolaPrecio();

        consola=new String[consolaPrecio.size()];
        jugadores=new String[consolaPrecio.size()];
        precio=new String[consolaPrecio.size()];
        ids=new int[consolaPrecio.size()];

        for (int i=0; i<consolaPrecio.size(); i++)
        {
            String[] partes=consolaPrecio.get(i).split(";");
            ids[i]=Integer.parseInt(partes[0]);
            consola[i]=partes[1];
            jugadores[i]=partes[2];
            precio[i]=partes[3];
        }
        adaptadorConsolaPrecio.UpdateConsolaPrecio(consola, jugadores,precio, ids);
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshConsolaPrecio();
    }

    @Override
    public void onEditar(int position) {
        Intent intent= new Intent(requireContext(), ConsolaPrecioEdits.class);
        intent.putExtra("precio", precio[position]);
        intent.putExtra("njugadores",jugadores[position]);
        intent.putExtra("id", ids[position]);
        startActivity(intent);
    }
    @Override
    public void onBorrar(int position) {
        consolaSeleccionada=position;
        toggleDeleteLayout();
    }

    private void toggleDeleteLayout()
    {
        if(deleteLayout.getVisibility()==View.VISIBLE)
        {
            deleteLayout.setVisibility(View.INVISIBLE);
        }
        else
        {
            deleteLayout.setVisibility(View.VISIBLE);
        }
    }
}