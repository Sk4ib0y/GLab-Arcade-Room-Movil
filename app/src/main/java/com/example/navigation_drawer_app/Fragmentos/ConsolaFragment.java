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

import com.example.navigation_drawer_app.Adaptadores.AdaptadorConsola;
import com.example.navigation_drawer_app.Actividades.NoDisponible;
import com.example.navigation_drawer_app.Altas.ConsolaAltas;
import com.example.navigation_drawer_app.Clases.ConsolaDAO;
import com.example.navigation_drawer_app.Clases.FragmentActions;
import com.example.navigation_drawer_app.Clases.Listener;
import com.example.navigation_drawer_app.Clases.SesionManager;
import com.example.navigation_drawer_app.Edits.ConsolaEdits;
import com.example.navigation_drawer_app.R;

import java.util.ArrayList;


public class ConsolaFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener, FragmentActions, Listener {

    int imagen[]={R.drawable.xbox};
    String consola[], estado[], observacion[];
    int ids[];
    ImageView mas;
    ListView listita;
    TextView añadir;
    SesionManager sesionManager;
    FrameLayout deleteLayout;
    Button cancelar, eliminar;
    AdaptadorConsola adaptadorConsola;
    ConsolaDAO consolaDAO;
    Listener listener;
    public int consolaSeleccionada=-1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_inventario, container, false);

        mas=view.findViewById(R.id.mas);
        añadir=view.findViewById(R.id.añadir);
        listita=view.findViewById(R.id.listita);
        deleteLayout=view.findViewById(R.id.deletelayout);
        cancelar=view.findViewById(R.id.cancelar);
        eliminar=view.findViewById(R.id.eliminar);

        cancelar.setOnClickListener(this);
        eliminar.setOnClickListener(this);
        mas.setOnClickListener(this);
        listita.setOnItemClickListener(this);

        consolaDAO= new ConsolaDAO(requireContext());
        consola= new String[0];
        estado=new String[0];
        observacion=new String[0];

        listener=this;

        adaptadorConsola = new AdaptadorConsola(requireContext(), consola, observacion, estado, imagen, ids, getLayoutInflater(), listener, true);
        listita.setAdapter(adaptadorConsola);
        refreshConsolas();


        sesionManager=new SesionManager(requireContext());
        boolean login=sesionManager.isAdmin();

        if (!login)
        {
         añadir.setVisibility(View.GONE);
         mas.setVisibility(View.GONE);
        }


        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(requireContext(), "La consolaprecio es "+consola[position]+  "\nEsta "+estado[position]+"\nalgunas observaciones "+observacion[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if(id==mas.getId())
        {
            Intent intent= new Intent(requireContext(), ConsolaAltas.class);
            startActivity(intent);
        }
        else if(cancelar.getId()==id)
        {
            toggleDeleteLayout();
        }
        else if(eliminar.getId()==id)
        {
            int idReal=ids[consolaSeleccionada];
            boolean borrado=consolaDAO.eliminar(idReal);

            if (borrado)
            {
                Toast.makeText(requireContext(), "El precio de la consola fue borrado correctamente", Toast.LENGTH_SHORT).show();
                toggleDeleteLayout();
                refreshConsolas();
            }
            else
            {
                Toast.makeText(requireContext(), "Error al borrar el precio", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void refreshConsolas()
    {
        ArrayList<String> consolas=consolaDAO.verConsolas();

        consola= new String[consolas.size()];
        estado= new String[consolas.size()];
        observacion=new String[consolas.size()];
        ids=new int[consolas.size()];

        for (int i=0; i<consolas.size(); i++)
        {
            String[] partes=consolas.get(i).split(";");
            ids[i]=Integer.parseInt(partes[0]);
            consola[i]=partes[1];
            estado[i]=partes[2];

            if(partes.length>3) {
                observacion[i] = partes[3];
            }
            else {
                observacion[i]="Sin observaciones";
            }
        }
        adaptadorConsola.UpdateConsolas(consola,estado,observacion, ids);
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshConsolas();
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

    @Override
    public void onEditar(int position) {
        Intent intent = new Intent(requireContext(), ConsolaEdits.class);
        intent.putExtra("estado", estado[position]);
        intent.putExtra("observaciones", observacion[position]);
        intent.putExtra("id", ids[position]);
        startActivity(intent);
    }

    @Override
    public void onBorrar(int position) {
        toggleDeleteLayout();
    }


}