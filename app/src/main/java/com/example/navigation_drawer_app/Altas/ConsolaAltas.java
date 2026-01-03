package com.example.navigation_drawer_app.Altas;

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

import com.example.navigation_drawer_app.Actividades.NoDisponible;
import com.example.navigation_drawer_app.Clases.ConsolaDAO;
import com.example.navigation_drawer_app.R;

public class ConsolaAltas extends AppCompatActivity implements View.OnClickListener {

    ImageView atras;
    Button añadir;
    RadioGroup rgConsola, rgEstado;
    EditText observaciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_consola_altas);

        atras=findViewById(R.id.atras);
        añadir=findViewById(R.id.añadir);
        rgConsola=findViewById(R.id.rgConsola);
        rgEstado=findViewById(R.id.rgEstado);
        observaciones=findViewById(R.id.observaciones);

        atras.setOnClickListener(this);
        añadir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();

        if(atras.getId()==id)
        {
            finish();
        }
        else if(añadir.getId()==id)
        {

            int selectedConsola=rgConsola.getCheckedRadioButtonId();
            int selectedEstado=rgEstado.getCheckedRadioButtonId();

            if(selectedConsola==-1)
            {
                Toast.makeText(this, "Favor de seleccionar una consola", Toast.LENGTH_SHORT).show();
                return;
            }

            if(selectedEstado==-1)
            {
                Toast.makeText(this, "Favor de indicar el estado de la consola", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selectedConsolaRB=findViewById(selectedConsola);
            RadioButton selectedEstadoRB=findViewById(selectedEstado);

            String consola=selectedConsolaRB.getText().toString().trim();
            String estado=selectedEstadoRB.getText().toString().trim();
            String obs=observaciones.getText().toString().trim();

            ConsolaDAO dao= new ConsolaDAO(this);
            long resultado=dao.insertarConsola(consola,estado,obs);

            if(resultado!=-1) {
                Toast.makeText(this, "Consola registrada", Toast.LENGTH_SHORT).show();
                finish();
            }
            else
            {
                Toast.makeText(this, "Error al añadir la consola", Toast.LENGTH_SHORT).show();
            }
        }
    }
}