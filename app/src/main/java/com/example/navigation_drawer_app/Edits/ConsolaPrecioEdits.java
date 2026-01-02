package com.example.navigation_drawer_app.Edits;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.navigation_drawer_app.Clases.ConsolaPrecioDAO;
import com.example.navigation_drawer_app.R;

public class ConsolaPrecioEdits extends AppCompatActivity implements View.OnClickListener {

    ImageView atras;
    EditText precio, njugadores;
    Button editar;
    ConsolaPrecioDAO consolaPrecioDAO;
    String jugadoresOriginales, precioOriginal;
    int idConsola;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_consola_precio_edits);

        consolaPrecioDAO=new ConsolaPrecioDAO(this);
        atras=findViewById(R.id.atras);
        precio=findViewById(R.id.precio);
        njugadores=findViewById(R.id.njugadores);
        editar=findViewById(R.id.editar);

        atras.setOnClickListener(this);
        editar.setOnClickListener(this);

        Intent intent= getIntent();
        precioOriginal=intent.getStringExtra("precio");
        jugadoresOriginales=intent.getStringExtra("njugadores");
        idConsola=intent.getIntExtra("id",-1);

        precio.setText(precioOriginal);
        njugadores.setText(jugadoresOriginales);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if(id==atras.getId())
        {
            finish();
        }
        else if(id==editar.getId())
        {
            String precioNuevo=precio.getText().toString().trim();
            String jugadoresNuevos=njugadores.getText().toString().trim();

            if(precioNuevo.isEmpty() || jugadoresNuevos.isEmpty())
            {
                Toast.makeText(this, "Todos los campos deben rellenarse", Toast.LENGTH_SHORT).show();
                return;
            }

            float preciof;
            int jugadoresf;
            try {
                preciof=Float.parseFloat(precioNuevo);
                jugadoresf=Integer.parseInt(jugadoresNuevos);
            }catch (NumberFormatException e)
            {
                Toast.makeText(this, "Los valores deben ser numericos", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean editado=consolaPrecioDAO.edit(idConsola,preciof, jugadoresf);

            if (editado)
            {
                Toast.makeText(this, "Precio editado correctamente", Toast.LENGTH_SHORT).show();
                finish();
            }
            else
            {
                Toast.makeText(this, "Error al editar", Toast.LENGTH_SHORT).show();
            }
        }
    }
}