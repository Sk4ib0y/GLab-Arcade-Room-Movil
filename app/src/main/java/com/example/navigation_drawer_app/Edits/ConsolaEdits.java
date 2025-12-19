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

import com.example.navigation_drawer_app.Clases.ConsolaDAO;
import com.example.navigation_drawer_app.R;

public class ConsolaEdits extends AppCompatActivity implements View.OnClickListener {

    ImageView volver;
    Button editar;
    EditText observaciones;
    RadioGroup rgEstado;
    String obsOriginales, estadoOriginal;
    int idConsola;
    ConsolaDAO consolaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_consola_edits);

        volver=findViewById(R.id.atras);
        editar=findViewById(R.id.editar);
        observaciones=findViewById(R.id.observaciones);

        rgEstado=findViewById(R.id.rgEstado);


        volver.setOnClickListener(this);
        editar.setOnClickListener(this);

        consolaDAO= new ConsolaDAO(this);

        Intent intent= getIntent();
        estadoOriginal=intent.getStringExtra("estado");
        obsOriginales=intent.getStringExtra("observaciones");
        idConsola=intent.getIntExtra("id", -1);


        observaciones.setText(obsOriginales);

        if(estadoOriginal.equals("Disponible"))
        {
            rgEstado.check(R.id.disponible);
        }
        else if(estadoOriginal.equals("En Uso"))
        {
            rgEstado.check(R.id.enuso);
        }
        else if(estadoOriginal.equals("En Reparacion"))
        {
            rgEstado.check(R.id.enreparacion);
        }
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();

        if(volver.getId()==id)
        {
            finish();
        }
        else if(editar.getId()==id)
        {
            RadioButton selectedEstado=findViewById(rgEstado.getCheckedRadioButtonId());
            String nuevoEstado=selectedEstado.getText().toString().trim();

            String nuevaObs=observaciones.getText().toString().trim();

            if (nuevoEstado.isEmpty())
            {
                Toast.makeText(this, "Favor de llenar el estado", Toast.LENGTH_SHORT).show();
            }
            boolean editado= consolaDAO.edit(idConsola, nuevoEstado, nuevaObs);
            
            if(editado)
            {
                Toast.makeText(this, "El producto fue editado correctamente", Toast.LENGTH_SHORT).show();
                finish();
            }
            else
            {
                Toast.makeText(this, "Error al editar producto", Toast.LENGTH_SHORT).show();
            }
        }
    }
}