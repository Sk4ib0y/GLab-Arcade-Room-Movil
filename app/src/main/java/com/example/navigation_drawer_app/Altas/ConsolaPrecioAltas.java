package com.example.navigation_drawer_app.Altas;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.navigation_drawer_app.Actividades.NoDisponible;
import com.example.navigation_drawer_app.Adaptadores.AdaptadorConsola;
import com.example.navigation_drawer_app.Clases.ConsolaDAO;
import com.example.navigation_drawer_app.Clases.ConsolaPrecioDAO;
import com.example.navigation_drawer_app.Clases.FragmentActions;
import com.example.navigation_drawer_app.Clases.Listener;
import com.example.navigation_drawer_app.Fragmentos.ConsolaFragment;
import com.example.navigation_drawer_app.R;

import java.util.ArrayList;

public class ConsolaPrecioAltas extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, Listener {

    ImageView volver;
    ListView listita;
    EditText precio, njugadores;
    Button añadir;
    String consola[];
    String observaciones[], consolaSeleccionada;
    String estado[];
    int imagen[], ids[];
    int idSeleccionado=-1;
    TextView textoconsola;
    AdaptadorConsola adaptadorConsola;
    Listener listener;
    ConsolaDAO consolaDAO;
    ConsolaPrecioDAO consolaPrecioDAO;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_consola_precio_altas);

        consolaDAO= new ConsolaDAO(this);
        consolaPrecioDAO=new ConsolaPrecioDAO(this);
        ArrayList<String>datos=consolaDAO.verConsolasSinPrecio();

        int size=datos.size();
        consola=new String[size];
        estado=new String[size];
        observaciones=new String[size];
        imagen=new int[size];
        ids=new int[size];



        for (int i=0; i<size;i++)
        {
            String partes[]=datos.get(i).split(";");

            ids[i]=Integer.parseInt(partes[0]);
            consola[i]=partes[1];
            estado[i]=partes[2];
            if(partes.length>3)
            {
                observaciones[i]=partes[3];
            }
            else observaciones[i]="Sin observaciones";
            imagen[i]=R.drawable.xbox;
        }

        volver=findViewById(R.id.atras);
        textoconsola=findViewById(R.id.textoconsola);
        listita=findViewById(R.id.listita);
        precio=findViewById(R.id.precio);
        njugadores=findViewById(R.id.njugadores);
        añadir=findViewById(R.id.añadir);

        volver.setOnClickListener(this);
        listita.setOnItemClickListener(this);
        añadir.setOnClickListener(this);

        listener=this;

        adaptadorConsola = new AdaptadorConsola(this, consola, observaciones, estado, imagen, ids, getLayoutInflater(), listener, false );
        listita.setAdapter(adaptadorConsola);

        if(datos.isEmpty())
        {
            listita.setVisibility(View.GONE);
            textoconsola.setText("No hay consolas para mostrar");
        }

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();

        if(id==volver.getId())
        {
            finish();
        }

        else if(id==añadir.getId())
        {
            if(idSeleccionado==-1)
            {
                Toast.makeText(this, "Debes seleccionar una consola", Toast.LENGTH_SHORT).show();
                return;
            }
            if(precio.getText().toString().trim().isEmpty() || njugadores.getText().toString().trim().isEmpty())
            {
                Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }
            float preciof;
            int njugadorf;
            try {
                preciof=Float.parseFloat(precio.getText().toString());
                njugadorf=Integer.parseInt(njugadores.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(this, "El precio debe ser numerico", Toast.LENGTH_SHORT).show();
                return;
            }

            consolaPrecioDAO.insertarConsolaPrecio(preciof, njugadorf, idSeleccionado);
            Toast.makeText(this, "Se añadio correctamente la consola", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        idSeleccionado= ids[position];
        consolaSeleccionada=consola[position];

        Toast.makeText(this, "Se ha seleccionado "+consolaSeleccionada, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEditar(int position) {

    }

    @Override
    public void onBorrar(int position) {

    }
}