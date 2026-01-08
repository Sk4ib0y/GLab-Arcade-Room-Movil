package com.example.navigation_drawer_app.Altas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.navigation_drawer_app.Clases.VideojuegoDAO;
import com.example.navigation_drawer_app.R;

public class VideojuegoAltas extends AppCompatActivity implements View.OnClickListener {

    ImageView atras;
    EditText nombre, genero;
    Button añadir;
    RadioGroup rgPlataforma;
    VideojuegoDAO videojuegoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_videojuego_altas);

        atras=findViewById(R.id.atras);
        nombre=findViewById(R.id.nombre);
        genero=findViewById(R.id.genero);
        añadir=findViewById(R.id.añadir);
        rgPlataforma=findViewById(R.id.rgPlataforma);

        atras.setOnClickListener(this);
        añadir.setOnClickListener(this);
        videojuegoDAO=new VideojuegoDAO(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();

        if(id==atras.getId())
        {
            finish();
        }
        if(id==añadir.getId())
        {
            int selectedPlataforma=rgPlataforma.getCheckedRadioButtonId();

            if(selectedPlataforma==-1)
            {
                Toast.makeText(this, "Favor de seleccionar una plataforma", Toast.LENGTH_SHORT).show();
                return;
            }
            String nombreS=nombre.getText().toString().trim();
            String generoS=genero.getText().toString().trim();

            if(nombreS.isEmpty() || generoS.isEmpty())
            {
                Toast.makeText(this, "Favor de rellenar todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selectedPlataformaRB=findViewById(selectedPlataforma);
            String plataforma=selectedPlataformaRB.getText().toString().trim();

            videojuegoDAO.insertarVideojuego(nombreS, generoS, plataforma);
            Toast.makeText(this, "Videojuego añadido correctamente!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}