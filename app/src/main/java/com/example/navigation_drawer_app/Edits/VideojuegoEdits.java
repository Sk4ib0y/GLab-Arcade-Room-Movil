package com.example.navigation_drawer_app.Edits;

import android.content.Intent;
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

public class VideojuegoEdits extends AppCompatActivity implements View.OnClickListener {

    Button editar;
    ImageView atras;
    EditText nombre, genero;
    RadioGroup plataforaRg;
    String videojuegoOriginal, generoOriginal, plataformaOriginal;
    int idVideojuego;
    VideojuegoDAO videojuegoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_videojuego_edits);

        editar=findViewById(R.id.editar);
        atras=findViewById(R.id.atras);
        nombre=findViewById(R.id.nombre);
        genero=findViewById(R.id.genero);
        plataforaRg=findViewById(R.id.rgPlataforma);

        videojuegoDAO=new VideojuegoDAO(this);

        editar.setOnClickListener(this);
        atras.setOnClickListener(this);

        Intent intent= getIntent();

        videojuegoOriginal=intent.getStringExtra("videojuego");
        generoOriginal=intent.getStringExtra("genero");
        idVideojuego=intent.getIntExtra("id",-1);
        plataformaOriginal=intent.getStringExtra("plataforma");

        nombre.setText(videojuegoOriginal);
        genero.setText(generoOriginal);
        if(plataformaOriginal.equals("Xbox"))
        {
            plataforaRg.check(R.id.xbox);
        }
        else if(plataformaOriginal.equals("PlayStation"))
        {
            plataforaRg.check(R.id.playstation);
        }
        else if(plataformaOriginal.equals("Nintendo"))
        {
            plataforaRg.check(R.id.nintendo);
        }
        else if(plataformaOriginal.equals("PC"))
        {
            plataforaRg.check(R.id.pc);
        }
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
            RadioButton selectedPlataforma=findViewById(plataforaRg.getCheckedRadioButtonId());
            String nuevaPlataforma=selectedPlataforma.getText().toString().trim();
            String nuevoGenero=genero.getText().toString().trim();
            String nuevoVideojuego=nombre.getText().toString().trim();
            
            if(nuevoGenero.isEmpty() || nuevoVideojuego.isEmpty() || nuevaPlataforma.isEmpty())
            {
                Toast.makeText(this, "Favor de rellenar todos los campos", Toast.LENGTH_SHORT).show();
            }
            
            boolean editado=videojuegoDAO.edit(idVideojuego,nuevoVideojuego, nuevoGenero, nuevaPlataforma);
            
            if(editado)
            {
                Toast.makeText(this, "Videojuego editado correctamente", Toast.LENGTH_SHORT).show();
                finish();
            }
            else
            {
                Toast.makeText(this, "Error al editar", Toast.LENGTH_SHORT).show();
            }
                
        }
    }
}