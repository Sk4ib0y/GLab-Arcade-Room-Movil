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

import com.example.navigation_drawer_app.Clases.MenuDAO;
import com.example.navigation_drawer_app.Fragmentos.MenuFragment;
import com.example.navigation_drawer_app.R;

public class MenuEdits extends AppCompatActivity implements View.OnClickListener {

    ImageView volver;
    Button editar;
    EditText productoText,precioText;
    String nombreOriginal;
    String precioOriginal;
    float precioOriginalf;
    MenuDAO menuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_edits);

        volver=findViewById(R.id.atras);
        editar=findViewById(R.id.editar);

        productoText=findViewById(R.id.producto);
        precioText=findViewById(R.id.precio);

        menuDAO=new MenuDAO(this);

        volver.setOnClickListener(this);
        editar.setOnClickListener(this);

        Intent intent= getIntent();
        nombreOriginal=intent.getStringExtra("producto");
        precioOriginal=intent.getStringExtra("precio");

        if (precioOriginal==null)
        {
            Toast.makeText(this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        productoText.setText(nombreOriginal);


        precioText.setText(precioOriginal);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();

        if(id==volver.getId())
        {
            finish();
        }
        else if(id==editar.getId())
        {
            String nuevoElemento=productoText.getText().toString().trim();
            String nuevoPrecio=precioText.getText().toString().trim();

            if(nuevoPrecio.isEmpty() || nuevoElemento.isEmpty())
            {
                Toast.makeText(this, "Todos los campos deben llenarse", Toast.LENGTH_SHORT).show();
            }

            float preciof;
            try {
                preciof=Float.parseFloat(nuevoPrecio);
            }catch (NumberFormatException e)
            {
                Toast.makeText(this, "El precio debe ser numerico", Toast.LENGTH_SHORT).show();
                return;
            }
            boolean editado=menuDAO.edit(nombreOriginal, nuevoElemento, preciof);

            if (editado)
            {
                Toast.makeText(this, "Elemento editado correctamente!", Toast.LENGTH_SHORT).show();
                finish();
            }
            else
            {
                Toast.makeText(this, "Error al editar", Toast.LENGTH_SHORT).show();
            }
        }

    }
}