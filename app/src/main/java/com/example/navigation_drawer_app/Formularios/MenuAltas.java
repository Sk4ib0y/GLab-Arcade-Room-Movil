package com.example.navigation_drawer_app.Formularios;

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

import com.example.navigation_drawer_app.Actividades.NoDisponible;
import com.example.navigation_drawer_app.Clases.MenuDAO;
import com.example.navigation_drawer_app.R;

public class MenuAltas extends AppCompatActivity implements View.OnClickListener {

    ImageView atras;
    EditText precio,producto;
    Button añadir;
    MenuDAO menuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_altas);

        atras=findViewById(R.id.atras);
        precio=findViewById(R.id.precio);
        producto=findViewById(R.id.producto);
        añadir=findViewById(R.id.añadir);

        atras.setOnClickListener(this);
        añadir.setOnClickListener(this);

        menuDAO= new MenuDAO(this);

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();

        if(id==atras.getId())
        {
            finish();
        }
        else if(id==añadir.getId())
        {
            String element=producto.getText().toString().trim();
            String price=precio.getText().toString().trim();

            if(element.isEmpty() || price.isEmpty())
            {
                Toast.makeText(this, "Todos los campos deben de rellenarse", Toast.LENGTH_SHORT).show();
                return;
            }

            float preciof;
                    try {
                        preciof=Float.parseFloat(price);
                    }catch (NumberFormatException e)
                    {
                        Toast.makeText(this, "El precio debe ser numerico", Toast.LENGTH_SHORT).show();
                        return;
                    }

            if (menuDAO.existe(element, preciof))
            {
                Toast.makeText(this, "El producto ya existe en el menu", Toast.LENGTH_SHORT).show();
                return;
            }

            long resultado=menuDAO.insertarProducto(element, preciof);

            if(resultado!=-1)
            {
                Toast.makeText(this, "¡Producto añadido correctamente!", Toast.LENGTH_SHORT).show();
                finish();
            }
            else
            {
                Toast.makeText(this, "Error al añadir el producto", Toast.LENGTH_SHORT).show();
            }
        }
    }
}